package bilibili.v1.test;

import bilibili.v1.mapper.FooMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class FooMapperTest {

    private static final Logger LOG = LoggerFactory.getLogger(FooMapperTest.class);

    @Resource
    FooMapper fooMapper;

    @Test
    public void contextLoads() {
        LocalDateTime dbNow = fooMapper.getDBNow();
        LOG.info("数据库时间: {}", dbNow);
    }

}
