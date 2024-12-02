package zzjjcc.config.mariadb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MariaDataSourceConfig {
    @Bean(name = "mariaHrdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.hrdb")
    public DataSource mariaHrdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariaMyTestDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.mytest")
    public DataSource mariaMyTestDataSource() {
        return DataSourceBuilder.create().build();
    }
}