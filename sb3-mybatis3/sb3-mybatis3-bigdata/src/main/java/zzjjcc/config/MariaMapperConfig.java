package zzjjcc.config;

import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "zzjjcc.mapper.maria",
        sqlSessionTemplateRef = "mariaSqlSessionTemplate")
public class MariaMapperConfig {

    @Resource
    private DataSource mariaDataSource;

    @Resource
    org.apache.ibatis.session.Configuration globalMybatisConfiguration;

    @Bean(name = "mariaSqlSessionFactory")
    @Primary
    public SqlSessionFactory mariaSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mariaDataSource);
        sqlSessionFactoryBean.setConfiguration(globalMybatisConfiguration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/maria/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mariaTransactionManager")
    @Primary
    public DataSourceTransactionManager mariaTransactionManager() {
        return new DataSourceTransactionManager(mariaDataSource);
    }

    @Bean(name = "mariaSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mariaSqlSessionTemplate(@Qualifier("mariaSqlSessionFactory") SqlSessionFactory mariaSqlSessionFactory) {
        return new SqlSessionTemplate(mariaSqlSessionFactory);
    }

}