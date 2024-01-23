package org.zjc.smalltools.components;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.zjc.smalltools.vo.request.BaseRequest;

import java.lang.reflect.Method;

@Component
public class SwitchDataSourceComponent {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory switchDataSource(BaseRequest baseRequest) {
        String jdbcUrl = baseRequest.getJdbcUrl();
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(baseRequest.getUsername());
        dataSource.setPassword(baseRequest.getPassword());
        Configuration configuration = sqlSessionFactory.getConfiguration();
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        Environment newEnvironment = new Environment(jdbcUrl, new JdbcTransactionFactory(), dataSource);
        defaultSqlSessionFactory.getConfiguration().setEnvironment(newEnvironment);
        return defaultSqlSessionFactory;
    }

    public <T, R> R executeMapperMethod(BaseRequest baseRequest, Class<T> clz, Method method, Object... args) {
        SqlSessionFactory factory = switchDataSource(baseRequest);
        try (SqlSession sqlSession = factory.openSession()) {
            T mapper = sqlSession.getMapper(clz);
            return (R) ReflectionUtils.invokeMethod(method, mapper, args);
        }
    }

}
