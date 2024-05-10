package zzjjcc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.hrdb")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.hrdb")
    public DataSource mariaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pgsqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pgsql.hrdb")
    public DataSource pgsqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}