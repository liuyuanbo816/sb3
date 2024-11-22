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
@MapperScan(basePackages = "zzjjcc.mapper.maria.esb",
        sqlSessionTemplateRef = "mariaEsbSqlSessionTemplate")
public class MariaEsbSqlSessionFactoryConfig {

    @Resource
    private DataSource mariaEsbDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration.maria.esb")
    public org.apache.ibatis.session.Configuration mariaEsbMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "mariaEsbSqlSessionFactory")
    public SqlSessionFactory mariaHrdbSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mariaEsbDataSource);
        sqlSessionFactoryBean.setConfiguration(mariaEsbMybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/maria/esb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mariaEsbTransactionManager")
    public DataSourceTransactionManager mariaEsbTransactionManager() {
        return new DataSourceTransactionManager(mariaEsbDataSource);
    }

    @Bean(name = "mariaEsbSqlSessionTemplate")
    public SqlSessionTemplate mariaEqbSqlSessionTemplate(@Qualifier("mariaEsbSqlSessionFactory") SqlSessionFactory mariaEsbSqlSessionFactory) {
        return new SqlSessionTemplate(mariaEsbSqlSessionFactory);
    }

}