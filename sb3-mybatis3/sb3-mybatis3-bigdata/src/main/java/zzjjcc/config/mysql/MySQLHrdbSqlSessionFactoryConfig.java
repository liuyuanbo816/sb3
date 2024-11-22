package zzjjcc.config.mysql;

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
@MapperScan(basePackages = "zzjjcc.mapper.mysql.hrdb",
        sqlSessionTemplateRef = "mysqlHrdbSqlSessionTemplate")
public class MySQLHrdbSqlSessionFactoryConfig {

    @Resource
    private DataSource mysqlHrdbDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration.mysql.hrdb")
    public org.apache.ibatis.session.Configuration mysqlHrdbMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "mysqlHrdbSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mysqlHrdbDataSource);
        sqlSessionFactoryBean.setConfiguration(mysqlHrdbMybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/mysql/hrdb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mysqlHrdbTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlHrdbTransactionManager() {
        return new DataSourceTransactionManager(mysqlHrdbDataSource);
    }

    @Bean(name = "mysqlHrdbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mysqlHrdbSqlSessionTemplate(@Qualifier("mysqlHrdbSqlSessionFactory") SqlSessionFactory mysqlHrdbSqlSessionFactory) {
        return new SqlSessionTemplate(mysqlHrdbSqlSessionFactory);
    }

}