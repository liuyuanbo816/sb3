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

    @Bean(name = "mariaEsbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.esb")
    public DataSource mariaEsbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariaEqbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.eqb")
    public DataSource mariaEqbDataSource() {
        return DataSourceBuilder.create().build();
    }
}