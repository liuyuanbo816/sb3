package org.zmz.sb3.newfeatures.test.env;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.sb3.newfeatures.env.DiEnvProperty;
import org.zmz.sb3.newfeatures.j17.properties.RedisCustomConfigProperties;

@SpringBootTest
public class DiEnvPropertyTest {

    public static final Logger LOG = LoggerFactory.getLogger(DiEnvPropertyTest.class);

    @Autowired
    DiEnvProperty envProperty;

    @Autowired
    RedisCustomConfigProperties redisCustomConfigProperties;

    @Test
    void testGetEnvProperty() {
        envProperty.printEnvConfig();
    }

    @Test
    void testRedisCustomConfigProperties() {
        LOG.info("{}", redisCustomConfigProperties);
    }
}
