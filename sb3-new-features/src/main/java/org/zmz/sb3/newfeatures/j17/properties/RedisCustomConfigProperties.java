package org.zmz.sb3.newfeatures.j17.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis")
public class RedisCustomConfigProperties {
    private String host;
    private String password;
    private int database;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "RedisCustomConfigProperties{" +
                "host='" + host + '\'' +
                ", password='" + password + '\'' +
                ", database=" + database +
                '}';
    }
}
