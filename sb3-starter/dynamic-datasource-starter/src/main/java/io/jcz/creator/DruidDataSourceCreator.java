package io.jcz.creator;


import com.alibaba.druid.pool.DruidDataSource;
import io.jcz.config.DruidConfig;
import io.jcz.constants.DataSourceConstant;
import io.jcz.propterties.DataSourceProperty;

import javax.sql.DataSource;
import java.util.Objects;

public class DruidDataSourceCreator extends AbstractDataSourceCreator {
    @Override
    protected DataSource doCreateDataSource(DataSourceProperty dataSourceProperty) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(dataSourceProperty.getUsername());
        dataSource.setPassword(dataSourceProperty.getPassword());
        dataSource.setUrl(dataSourceProperty.getUrl());
        dataSource.setDriverClassName(dataSourceProperty.getDriverClassName());
        DruidConfig druidConfig = dataSourceProperty.getDruidConfig();
        if (Objects.nonNull(druidConfig)) {
            dataSource.configFromPropeties(druidConfig.toProperties());
        }
        try {
            dataSource.init();
        } catch (Exception e) {
            throw new RuntimeException("druid datasouce create error, e=", e);
        }
        return dataSource;
    }

    @Override
    public boolean support(DataSourceProperty dataSourceProperty) {
        Class<? extends DataSource> type = dataSourceProperty.getType();
        return Objects.nonNull(type) && Objects.equals(type.getName(), DataSourceConstant.DRUID_DATASOURCE);
    }
}
