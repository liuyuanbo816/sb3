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
    DataSource dataSource;

    @Test
    public void testContext() throws SQLException {
        if (dataSource != null) {
            LOG.info("{}", dataSource.getConnection());
        }
        if (mariaDataSource != null) {
            LOG.info("{}", mariaDataSource.getConnection());
        }
        if (pgsqlDataSource != null) {
            LOG.info("{}", pgsqlDataSource.getConnection());
        }
    }

}
