package boot.mybatis3.test.mapper.eqb;

import boot.mybatis3.mapper.PersonMapper;
import boot.mybatis3.pojo.Person;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PersonMapperTest {

    public static final Logger LOG = LoggerFactory.getLogger(PersonMapperTest.class);

    @Autowired
    PersonMapper personMapper;

    @Test
    public void testPersonMapper() {
        LocalDateTime dbNow = personMapper.getDBNow();
        LOG.info("当前时间: {}", dbNow);
        LOG.info("=======================================");
        Person person = personMapper.getPersonById(1L);
        LOG.info("根据id查询用户: {}", person);
        LOG.info("=======================================");
        List<Person> persons = personMapper.getPersons();
        LOG.info("查询所有用户: {}", persons);
        LOG.info("=======================================");
    }

}
