package zzjjcc.config.postgresql;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgreSQLDataSourceConfig {
    @Bean(name = "postgresqlHrdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql.hrdb")
    public DataSource postgresqlHrdbDataSource() {
        return DataSourceBuilder.create().build();
    }
}