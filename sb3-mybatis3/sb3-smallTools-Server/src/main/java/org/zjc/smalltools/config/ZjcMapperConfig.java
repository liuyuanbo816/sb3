package org.zjc.smalltools.config;

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
@MapperScan(basePackages = "org.zjc.smalltools.mapper",
        sqlSessionFactoryRef = "sqlSessionFactory")
public class ZjcMapperConfig {

    @Autowired
    private DataSource defaultDataSource;

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(defaultDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/default/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "defaultTransactionManager")
    @Primary
    public DataSourceTransactionManager defaultTransactionManager() {
        return new DataSourceTransactionManager(defaultDataSource);
    }

    @Bean(name = "defaultSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate defaultSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}