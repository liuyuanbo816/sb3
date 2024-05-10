package zzjjcc.test.pgsql;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
public class BaseTest {

    public static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @Autowired
    DataSource pgsqlDataSource;

    @Test
    public void testContext() {
        LOG.info("{}", pgsqlDataSource);
    }

}
