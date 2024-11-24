package io.jcz.propterties;

import io.jcz.config.DruidConfig;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.sql.DataSource;

public class DataSourceProperty {
    /**
     * 连接池类型
     */
    private Class<? extends DataSource> type;
    /**
     * driver
     */
    private String driverClassName;

    private String url;

    private String username;
    private String password;
    /**
     * 用私钥进行解密
     */
    private String privateKey;

    @NestedConfigurationProperty
    private DruidConfig druidConfig;

    public Class<? extends DataSource> getType() {
        return type;
    }

    public void setType(Class<? extends DataSource> type) {
        this.type = type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public DruidConfig getDruidConfig() {
        return druidConfig;
    }

    public void setDruidConfig(DruidConfig druidConfig) {
        this.druidConfig = druidConfig;
    }
}
