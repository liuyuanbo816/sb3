package io.jcz.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import io.jcz.constants.DataSourceConstant;
import io.jcz.provider.DynamicDataSourceProvider;
import io.jcz.util.DynamicDataSourceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean, DisposableBean {

    public static final Logger LOG = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    private final List<DynamicDataSourceProvider> providers;

    private String primary = DataSourceConstant.PRIMARY;

    private final Map<String, DataSource> allDataSources = new ConcurrentHashMap<>();

    public DynamicRoutingDataSource(List<DynamicDataSourceProvider> providers) {
        this.providers = providers;
    }

    @Override
    protected DataSource determineDataSource() {
        String currentDataSource = DynamicDataSourceContextHolder.getCurrentDataSource();
        return getDataSource(currentDataSource);
    }

    private DataSource getDataSource(String dataSource) {
        if (!StringUtils.hasText(dataSource)) {
            return getDefaultDataSource();
        } else if (allDataSources.containsKey(dataSource)) {
            return allDataSources.get(dataSource);
        }
        return getDefaultDataSource();
    }

    /**
     * 返回默认数据源
     */
    private DataSource getDefaultDataSource() {
        DataSource dataSource = allDataSources.get(primary);
        if (dataSource != null) {
            return dataSource;
        }
        throw new RuntimeException("当前没有找到默认数据源");
    }


    public void addDataSource(String ds, DataSource dataSource) {
        DataSource oldDataSource = allDataSources.put(ds, dataSource);
        if (oldDataSource != null) {
            closeDataSource(ds, oldDataSource);
        }
        LOG.info("添加数据源 {} 成功", ds);
    }

    /**
     * 关闭数据源
     */
    private void closeDataSource(String ds, DataSource dataSource) {
        if (dataSource instanceof DruidDataSource dds) {
            dds.close();
        } else {
            try {
                Method close = ReflectionUtils.findMethod(dataSource.getClass(), "close");
                if (Objects.nonNull(close)) {
                    close.invoke(dataSource);
                }
            } catch (Exception e) {
                LOG.error("关闭数据源 {} 失败, e=", ds, e);
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(16);
        providers.stream().map(DynamicDataSourceProvider::loadDataSources).forEach(dataSourceMap::putAll);
        for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
            addDataSource(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void destroy() {
        LOG.info("关闭所有数据源");
        for (Map.Entry<String, DataSource> entry : allDataSources.entrySet()) {
            closeDataSource(entry.getKey(), entry.getValue());
        }
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }
}
