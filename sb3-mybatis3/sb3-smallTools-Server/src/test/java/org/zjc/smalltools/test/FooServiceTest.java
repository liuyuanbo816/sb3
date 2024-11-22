package org.zjc.smalltools.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zjc.smalltools.model.Person;
import org.zjc.smalltools.service.FooService;

@SpringBootTest
public class FooServiceTest {

    @Autowired
    FooService fooService;

    @Test
    public void t1() {
        Person person = new Person();
        person.setName("孙悟空");
        person.setAge(500);
        person.setEmail("sunwukong@gg.com");
        fooService.foo1(person);
    }
}
