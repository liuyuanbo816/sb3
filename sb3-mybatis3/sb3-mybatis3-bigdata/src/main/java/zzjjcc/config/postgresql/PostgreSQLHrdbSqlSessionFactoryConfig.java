package zzjjcc.config.postgresql;

import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "zzjjcc.mapper.pgsql.hrdb",
        sqlSessionTemplateRef = "pgsqlHrdbSqlSessionTemplate")
public class PostgreSQLHrdbSqlSessionFactoryConfig {

    @Resource
    private DataSource pgsqlHrdbDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration.postgresql.hrdb")
    public org.apache.ibatis.session.Configuration postgresqlMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "pgsqlHrdbSqlSessionFactory")
    public SqlSessionFactory pgsqlHrdbSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(pgsqlHrdbDataSource);
        sqlSessionFactoryBean.setConfiguration(postgresqlMybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/pgsql/hrdb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "pgsqlHrdbTransactionManager")
    public DataSourceTransactionManager pgsqlHrdbTransactionManager() {
        return new DataSourceTransactionManager(pgsqlHrdbDataSource);
    }

    @Bean(name = "pgsqlHrdbSqlSessionTemplate")
    public SqlSessionTemplate pgsqlHrdbSqlSessionTemplate(@Qualifier("pgsqlHrdbSqlSessionFactory") SqlSessionFactory pgsqlHrdbSqlSessionFactory) {
        return new SqlSessionTemplate(pgsqlHrdbSqlSessionFactory);
    }

}