package org.zmz.sb3.newfeatures.test.stream;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamCollectorsTest {

    @Test
    public void test1() {
        Map<Integer, Map<String, Map<Boolean, List<Person>>>> map = Stream.of(
                        new Person("貂蝉", 19, true),
                        new Person("貂蝉", 20, false),
                        new Person("王昭君", 20, true),
                        new Person("西施", 19, true),
                        new Person("西施2", 20, false),
                        new Person("杨玉环", 20, true)
                )
                .collect(
                        Collectors.groupingBy(Person::getAge,
                                Collectors.groupingBy(Person::getName,
                                        Collectors.groupingBy(Person::isSex)))
                );

        log.info("聚合结果: {}", map);
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class Person {
        private String name;
        private int age;
        private boolean sex;
    }

}
