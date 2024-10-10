package org.zjc.smalltools.components;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zjc.smalltools.vo.request.BaseRequest;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SwitchDataSourceComponent {
    @Resource
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private static final Map<Integer, SqlSessionFactory> sqlSessionFactoryMap = new ConcurrentHashMap<>();

    public SqlSessionFactory switchDataSource(String jdbcUrl, String userName, String password) {
        Integer key = Objects.hashCode(jdbcUrl + userName + password);
        SqlSessionFactory cacheSqlSessionFactory = sqlSessionFactoryMap.get(key);
        if (cacheSqlSessionFactory != null) {
            return cacheSqlSessionFactory;
        }
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        Configuration configuration = sqlSessionFactory.getConfiguration();
        Environment environment = new Environment(String.valueOf(key), new JdbcTransactionFactory(), dataSource);
        ConfigurationWrapper configurationWrapper = new ConfigurationWrapper(configuration, environment);
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configurationWrapper);
        sqlSessionFactoryMap.put(key, defaultSqlSessionFactory);
        return defaultSqlSessionFactory;
    }

    public SqlSession getSqlSessionFromRequest(BaseRequest request) {
        String jdbcUrl = request.getJdbcUrl();
        String userName = request.getUsername();
        String password = request.getPassword();
        SqlSessionFactory factory = switchDataSource(jdbcUrl, userName, password);
        return factory.openSession();
    }


}
