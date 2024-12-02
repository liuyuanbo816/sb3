package io.jcz.provider;

import io.jcz.propterties.DataSourceProperty;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 从 yaml 文件读取数据源属性,进行加载
 */
public class YamlDynamicDataSourceProvider extends AbstractDynamicDataSourceProvider {

    private final Map<String, DataSourceProperty> propertyMap;

    public YamlDynamicDataSourceProvider(Map<String, DataSourceProperty> propertyMap) {
        this.propertyMap = propertyMap;
    }

    @Override
    public Map<String, DataSource> loadDataSources() {
        return createDataSourceMap(this.propertyMap);
    }
}
