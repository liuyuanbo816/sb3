package org.zmz.sb3.redis.seckill.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.zmz.sb3.redis.seckill.mapper",
        sqlSessionTemplateRef = "mariadbMyTestSqlSessionTemplate")
public class MariaDBMyTestSqlSessionFactoryConfig {

    @Autowired
    @Qualifier("mariaMyTestDataSource")
    private DataSource mariaMyTestDataSource;

    @Bean(name = "mariadbMyTestSqlSessionFactory")
    @Primary
    public SqlSessionFactory mariadbMyTestSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mariaMyTestDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mariadbMyTestTransactionManager")
    @Primary
    public DataSourceTransactionManager mariadbMyTestTransactionManager() {
        return new DataSourceTransactionManager(mariaMyTestDataSource);
    }

    @Bean(name = "mariadbMyTestSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate eqbSqlSessionTemplate(@Qualifier("mariadbMyTestSqlSessionFactory") SqlSessionFactory mariadbMyTestSqlSessionFactory) {
        return new SqlSessionTemplate(mariadbMyTestSqlSessionFactory);
    }

}