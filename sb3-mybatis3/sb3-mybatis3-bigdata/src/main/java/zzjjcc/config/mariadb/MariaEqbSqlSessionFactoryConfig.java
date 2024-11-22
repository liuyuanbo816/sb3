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
@MapperScan(basePackages = "zzjjcc.mapper.maria.eqb",
        sqlSessionTemplateRef = "mariaEqbSqlSessionTemplate")
public class MariaEqbSqlSessionFactoryConfig {

    @Resource
    private DataSource mariaEqbDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration.maria.eqb")
    public org.apache.ibatis.session.Configuration mariaEqbMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "mariaEqbSqlSessionFactory")
    public SqlSessionFactory mariaEqbSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mariaEqbDataSource);
        sqlSessionFactoryBean.setConfiguration(mariaEqbMybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/maria/eqb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mariaEqbTransactionManager")
    public DataSourceTransactionManager mariaEqbTransactionManager() {
        return new DataSourceTransactionManager(mariaEqbDataSource);
    }

    @Bean(name = "mariaEqbSqlSessionTemplate")
    public SqlSessionTemplate mariaEqbSqlSessionTemplate(@Qualifier("mariaEqbSqlSessionFactory") SqlSessionFactory mariaEqbSqlSessionFactory) {
        return new SqlSessionTemplate(mariaEqbSqlSessionFactory);
    }

}