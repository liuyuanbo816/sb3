package zzjjcc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "mysqlHrdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.hrdb")
    public DataSource mysqlHrdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariaHrdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.hrdb")
    public DataSource mariaHrdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pgsqlHrdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pgsql.hrdb")
    public DataSource pgsqlHrdbDataSource() {
        return DataSourceBuilder.create().build();
    }
}