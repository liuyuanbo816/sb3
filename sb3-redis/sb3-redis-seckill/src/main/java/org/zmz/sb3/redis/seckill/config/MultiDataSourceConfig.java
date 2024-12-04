package org.zmz.sb3.redis.seckill.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MultiDataSourceConfig {

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariaMyTestDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mariadb")
    public DataSource mariaMyTestDataSource() {
        return DataSourceBuilder.create().build();
    }

}
