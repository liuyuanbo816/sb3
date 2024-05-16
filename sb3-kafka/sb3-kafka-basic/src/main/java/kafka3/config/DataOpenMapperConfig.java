package kafka3.config;

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
@MapperScan(basePackages = "kafka3.mapper.dataopen",
        sqlSessionTemplateRef = "dataopenSqlSessionTemplate")
public class DataOpenMapperConfig {

    @Resource
    private DataSource dataopenDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration.mysql")
    public org.apache.ibatis.session.Configuration globalMySQLMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "dataopenSqlSessionFactory")
    @Primary
    public SqlSessionFactory dataopenSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataopenDataSource);
        sqlSessionFactoryBean.setConfiguration(globalMySQLMybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/dataopen/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dataopenTransactionManager")
    @Primary
    public DataSourceTransactionManager dataopenTransactionManager() {
        return new DataSourceTransactionManager(dataopenDataSource);
    }

    @Bean(name = "dataopenSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate dataopenSqlSessionTemplate(@Qualifier("dataopenSqlSessionFactory") SqlSessionFactory dataopenSqlSessionFactory) {
        return new SqlSessionTemplate(dataopenSqlSessionFactory);
    }

}