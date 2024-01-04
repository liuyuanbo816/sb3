package boot.mybatis3.config;

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
@MapperScan(basePackages = "boot.mybatis3.mapper.esb",
        sqlSessionTemplateRef = "esbSqlSessionTemplate")
public class EsbMapperConfig {

    @Autowired
    @Qualifier("esbDataSource")
    private DataSource esbDataSource;

    @Bean(name = "esbSqlSessionFactory")
    @Primary
    public SqlSessionFactory esbSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(esbDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/esb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "esbTransactionManager")
    @Primary
    public DataSourceTransactionManager esbTransactionManager() {
        return new DataSourceTransactionManager(esbDataSource);
    }

    @Bean(name = "esbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate esbSqlSessionTemplate(@Qualifier("esbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}