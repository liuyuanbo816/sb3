package zzjjcc.config;

import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "zzjjcc.mapper.pgsql",
        sqlSessionTemplateRef = "pgsqlSqlSessionTemplate")
public class PgSQLMapperConfig {

    @Resource
    private DataSource pgsqlDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration.pgsql")
    public org.apache.ibatis.session.Configuration globalPgSQLMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "pgsqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory pgsqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(pgsqlDataSource);
        sqlSessionFactoryBean.setConfiguration(globalPgSQLMybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/pgsql/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(pgsqlDataSource);
    }

    @Bean(name = "pgsqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate pgsqlSqlSessionTemplate(@Qualifier("pgsqlSqlSessionFactory") SqlSessionFactory pgsqlSqlSessionFactory) {
        return new SqlSessionTemplate(pgsqlSqlSessionFactory);
    }

}