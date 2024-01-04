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
@MapperScan(basePackages = "boot.mybatis3.mapper.isale",
        sqlSessionTemplateRef = "isaleSqlSessionTemplate")
public class IsaleMapperConfig {

    @Autowired
    @Qualifier("isaleDataSource")
    private DataSource isaleDataSource;

    @Bean(name = "isaleSqlSessionFactory")
    @Primary
    public SqlSessionFactory isaleSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(isaleDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/isale/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "isaleTransactionManager")
    @Primary
    public DataSourceTransactionManager isaleTransactionManager() {
        return new DataSourceTransactionManager(isaleDataSource);
    }

    @Bean(name = "isaleSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate isaleSqlSessionTemplate(@Qualifier("isaleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}