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
        sqlSessionTemplateRef = "pgsqlHrdbSqlSessionTemplate")
public class PgSQLHrdbMapperConfig {

    @Resource
    private DataSource pgsqlHrdbDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration.pgsql")
    public org.apache.ibatis.session.Configuration globalPgSQLMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "pgsqlHrdbSqlSessionFactory")
    @Primary
    public SqlSessionFactory pgsqlHrdbSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(pgsqlHrdbDataSource);
        sqlSessionFactoryBean.setConfiguration(globalPgSQLMybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/pgsql/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "pgsqlHrdbTransactionManager")
    @Primary
    public DataSourceTransactionManager pgsqlHrdbTransactionManager() {
        return new DataSourceTransactionManager(pgsqlHrdbDataSource);
    }

    @Bean(name = "pgsqlHrdbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate pgsqlHrdbSqlSessionTemplate(@Qualifier("pgsqlHrdbSqlSessionFactory") SqlSessionFactory pgsqlHrdbSqlSessionFactory) {
        return new SqlSessionTemplate(pgsqlHrdbSqlSessionFactory);
    }

}