package zzjjcc.config.mysql;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MySQLDataSourceConfig {
    @Bean(name = "mysqlHrdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.hrdb")
    @Primary
    public DataSource mysqlHrdbDataSource() {
        return DataSourceBuilder.create().build();
    }
}