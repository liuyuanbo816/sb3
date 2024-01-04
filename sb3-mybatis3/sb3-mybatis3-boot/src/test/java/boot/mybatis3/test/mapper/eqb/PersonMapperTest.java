package boot.mybatis3.test.mapper.eqb;

import boot.mybatis3.mapper.eqb.PersonMapper;
import boot.mybatis3.mapper.eqb.EqbTestMapper;
import boot.mybatis3.mapper.esb.EsbTestMapper;
import boot.mybatis3.mapper.isale.IsaleTestMapper;
import boot.mybatis3.pojo.Person;
import boot.mybatis3.pojo.eqb.EqbTest;
import boot.mybatis3.pojo.esb.EsbTest;
import boot.mybatis3.pojo.isale.IsaleTest;
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

    @Autowired
    EqbTestMapper eqbTestMapper;

    @Autowired
    EsbTestMapper esbTestMapper;

    @Autowired
    IsaleTestMapper isaleTestMapper;

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

    @Test
    public void testMultiDataSource() {
        List<EqbTest> eqbList = eqbTestMapper.list();
        LOG.info("查询EQB列表: {}", eqbList);
        LOG.info("=======================================");
        List<EsbTest> esbList = esbTestMapper.list();
        LOG.info("查询ESB列表: {}", esbList);
        LOG.info("=======================================");
        List<IsaleTest> isaleList = isaleTestMapper.list();
        LOG.info("查询ISALE列表: {}", isaleList);
    }

}
