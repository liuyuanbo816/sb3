package io.jcz.propterties;

import io.jcz.constants.DataSourceConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = DynamicDataSourceProperty.PREFIX)
public class DynamicDataSourceProperty {

    public static final String PREFIX = "spring.datasource.dynamic";

    //默认数据库名称
    private String primary = DataSourceConstant.PRIMARY;

    //所有的数据源
    private Map<String, DataSourceProperty> ds = new LinkedHashMap<>();

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public Map<String, DataSourceProperty> getDs() {
        return ds;
    }

    public void setDs(Map<String, DataSourceProperty> ds) {
        this.ds = ds;
    }
}
