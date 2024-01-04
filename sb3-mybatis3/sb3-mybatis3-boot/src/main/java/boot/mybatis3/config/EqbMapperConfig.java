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
@MapperScan(basePackages = "boot.mybatis3.mapper.eqb",
        sqlSessionTemplateRef = "eqbSqlSessionTemplate")
public class EqbMapperConfig {

    @Autowired
    @Qualifier("eqbDataSource")
    private DataSource eqbDataSource;

    @Bean(name = "eqbSqlSessionFactory")
    @Primary
    public SqlSessionFactory eqbSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(eqbDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/eqb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "eqbTransactionManager")
    @Primary
    public DataSourceTransactionManager eqbTransactionManager() {
        return new DataSourceTransactionManager(eqbDataSource);
    }

    @Bean(name = "eqbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate eqbSqlSessionTemplate(@Qualifier("eqbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}