package org.zmz.sb3.newfeatures.test.switches;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewSwitchTest {
    @Test
    public void test1() {
        int day = 2;
        testNewSwitch(day);
        System.out.println("=====================");
        int day1 = 7;
        testNewSwitch(day1);
        System.out.println("=====================");
        int day2 = 21;
        testNewSwitch(day2);
        System.out.println("=====================");
    }

    public static void testNewSwitch(int day) {
        String s = switch (day) {
            case 1, 2, 3, 4, 5 -> "工作日";
            case 6, 7 -> "休息日";
            default -> throw new IllegalArgumentException("非法时间");
        };
        System.out.println(s);
    }

    @Test
    public void test2() {
        List<String> lst1 = List.of("1", "2", "3");
        List<String> lst2 = List.of("1", "2", "3");

        System.out.println(Objects.equals(lst1, lst2));
    }

    @Test
    public void test3() {
        List<Integer> lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(2);
        lst1.add(3);
        List<Integer> lst2 = new ArrayList<>();
        lst2.add(1);
        lst2.add(2);
        lst2.add(3);

        System.out.println(Objects.equals(lst1, lst2));
    }
}
