package zzjjcc.test.maria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zzjjcc.service.CommonCodeToNameService;
import zzjjcc.service.my.MyEqbService;
import zzjjcc.service.my.MyEsbService;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MultipleDataSourceTest {

    @Autowired
    CommonCodeToNameService commonCodeToNameService;

    @Autowired
    MyEsbService myEsbService;

    @Autowired
    MyEqbService myEqbService;

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

    @Test
    public void testBarDDS() {
        commonCodeToNameService.barDDS(">>>>>>");
    }

    // ---------------------------------------------------------------------------------------------------

    @Test
    public void testMyEsb() {
        myEsbService.simpleInsertTbA1(">>>>>>");
        List<Map<String, Object>> list = myEsbService.list();
        list.forEach(System.out::println);
    }

    @Test
    public void testMyEqb() {
        myEqbService.simpleInsertTbB2("??????");
        //List<Map<String, Object>> list = myEqbService.list();
        //list.forEach(System.out::println);
    }


    // ---------------------------------------------------------------------------------------------------

}
