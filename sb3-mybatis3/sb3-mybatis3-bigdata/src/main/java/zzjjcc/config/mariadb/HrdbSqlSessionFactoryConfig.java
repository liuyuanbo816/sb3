package zzjjcc.config.mariadb;

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
@MapperScan(basePackages = "zzjjcc.mapper.mariadb.hrdb",
        sqlSessionTemplateRef = "mariadbHrdbSqlSessionTemplate")
public class HrdbSqlSessionFactoryConfig {

    @Autowired
    @Qualifier("mariaHrdbDataSource")
    private DataSource mariaHrdbDataSource;

    @Bean(name = "mariadbHrdbSqlSessionFactory")
    @Primary
    public SqlSessionFactory mariadbHrdbSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mariaHrdbDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/mariadb/hrdb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mariadbHrdbTransactionManager")
    @Primary
    public DataSourceTransactionManager mariadbHrdbTransactionManager() {
        return new DataSourceTransactionManager(mariaHrdbDataSource);
    }

    @Bean(name = "mariadbHrdbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate eqbSqlSessionTemplate(@Qualifier("mariadbHrdbSqlSessionFactory") SqlSessionFactory mariadbHrdbSqlSessionFactory) {
        return new SqlSessionTemplate(mariadbHrdbSqlSessionFactory);
    }

}