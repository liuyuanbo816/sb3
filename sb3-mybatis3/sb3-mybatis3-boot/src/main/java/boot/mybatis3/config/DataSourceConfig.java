package boot.mybatis3.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "eqbDataSource")
    @Qualifier("eqbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.eqb")
    @Primary
    public DataSource eqbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "isaleDataSource")
    @Qualifier("isaleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.isale")
    public DataSource isaleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "esbDataSource")
    @Qualifier("esbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.esb")
    public DataSource esbDataSource() {
        return DataSourceBuilder.create().build();
    }
}
