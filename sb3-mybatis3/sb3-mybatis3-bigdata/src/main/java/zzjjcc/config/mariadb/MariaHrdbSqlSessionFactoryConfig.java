package zzjjcc.config.mariadb;

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
@MapperScan(basePackages = "zzjjcc.mapper.maria.hrdb",
        sqlSessionTemplateRef = "mariaHrdbSqlSessionTemplate")
public class MariaHrdbSqlSessionFactoryConfig {

    @Resource
    private DataSource mariaHrdbDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration.maria.hrdb")
    public org.apache.ibatis.session.Configuration mariaHrdbMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "mariaHrdbSqlSessionFactory")
    public SqlSessionFactory mariaHrdbSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mariaHrdbDataSource);
        sqlSessionFactoryBean.setConfiguration(mariaHrdbMybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/maria/hrdb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mariaHrdbTransactionManager")
    public DataSourceTransactionManager mariaHrdbTransactionManager() {
        return new DataSourceTransactionManager(mariaHrdbDataSource);
    }

    @Bean(name = "mariaHrdbSqlSessionTemplate")
    public SqlSessionTemplate mariaSqlSessionTemplate(@Qualifier("mariaHrdbSqlSessionFactory") SqlSessionFactory mariaHrdbSqlSessionFactory) {
        return new SqlSessionTemplate(mariaHrdbSqlSessionFactory);
    }

}