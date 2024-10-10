package zzjjcc.test.pgsql;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public class BaseTest {

    public static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @Autowired
    DataSource pgsqlDataSource;

    @Autowired
    DataSource mariaDataSource;

    @Autowired
    DataSource mysqlDataSource;

    @Test
    public void testContext() throws SQLException {
        if (mysqlDataSource != null) {
            LOG.info("mysqlDataSource: {}", mysqlDataSource);
            LOG.info("mysqlDataSource.getConnection(): {}", mysqlDataSource.getConnection());
        }
        if (mariaDataSource != null) {
            LOG.info("mariaDataSource: {}", mariaDataSource);
            LOG.info("mariaDataSource.getConnection(): {}", mariaDataSource.getConnection());
        }
        if (pgsqlDataSource != null) {
            LOG.info("pgsqlDataSource: {}", pgsqlDataSource);
            LOG.info("pgsqlDataSource.getConnection(): {}", pgsqlDataSource.getConnection());
        }
    }

}
