package org.zmz.sb3.vertx.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CJTest {
    @Test
    public void tst1() {
        boolean a = true;
        boolean b = false;

        boolean c = a | b;
        System.out.println(c);
    }


    @Test
    public void tst2() {
        List<String> list = new ArrayList<>();

        list.add("c1");
        list.add("c2");
        list.add("c3");
        list.add("c4");
        list.add("c5");
        list.add("c6");

        list.add(0, "(");

        System.out.println(list);
        for (int i = 1; i < list.size() - 1; i += 2) {
            list.add(i + 1, "and");
        }
        list.add(list.size(), ")");

        System.out.println(list);
    }

    @Test
    public void tst3() {
        List<Integer> list = List.of(1, 2, 3, 4);
        List<Long> llist = list.stream().map(i -> (long) i).toList();
        System.out.println(llist);
    }
}
