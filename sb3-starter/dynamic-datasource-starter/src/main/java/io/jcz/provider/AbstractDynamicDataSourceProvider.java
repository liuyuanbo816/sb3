package io.jcz.provider;

import io.jcz.creator.DefaultDataSourceCreator;
import io.jcz.propterties.DataSourceProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDynamicDataSourceProvider implements DynamicDataSourceProvider {

    private DefaultDataSourceCreator defaultDataSourceCreator;

    @Autowired
    public void setDefaultDataSourceCreator(DefaultDataSourceCreator defaultDataSourceCreator) {
        this.defaultDataSourceCreator = defaultDataSourceCreator;
    }

    protected Map<String, DataSource> createDataSourceMap(Map<String, DataSourceProperty> propertyMap) {
        Map<String, DataSource> map = new HashMap<>();
        for (Map.Entry<String, DataSourceProperty> entry : propertyMap.entrySet()) {
            DataSource dataSource = defaultDataSourceCreator.createDataSource(entry.getValue());
            map.put(entry.getKey(), dataSource);
        }
        return map;
    }

}
