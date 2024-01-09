package boot.mybatis3.test.mapper.xpath;

import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

//@SpringBootTest
public class MyBatis3XpathTest {
    @Test
    public void test1() throws IOException {
        ClassPathResource resource = new ClassPathResource("mapper/esb/EsbTestMapper.xml");
        XPathParser parser = new XPathParser(resource.getInputStream(), true, null, new XMLMapperEntityResolver());
        XNode xNode = parser.evalNode("/mapper");
        System.out.println(xNode);
    }
}
