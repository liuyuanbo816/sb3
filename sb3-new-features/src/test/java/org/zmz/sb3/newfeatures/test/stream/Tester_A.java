package org.zmz.sb3.newfeatures.test.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Tester_A {

    @Test
    public void t1() {
        List<String> list = new ArrayList<>();

        List<String> rs = list.stream().map(s -> s + "").toList();
        System.out.println(rs);
    }

}
