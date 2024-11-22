package zzjjcc.test.maria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zzjjcc.service.CommonCodeToNameService;

@SpringBootTest
public class MultipleDataSourceTest {

    @Autowired
    CommonCodeToNameService commonCodeToNameService;

    @Test
    public void testFoo() {
        commonCodeToNameService.foo("超哥棒棒糖");
    }

    @Test
    public void testBar() {
        commonCodeToNameService.bar(">>>>>>");
    }

    @Test
    public void testBar1() {
        commonCodeToNameService.bar1(">>>>>>");
    }

}
