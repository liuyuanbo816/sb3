package org.zmz.sb3.newfeatures.test.switches;

import org.junit.jupiter.api.Test;

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
}
