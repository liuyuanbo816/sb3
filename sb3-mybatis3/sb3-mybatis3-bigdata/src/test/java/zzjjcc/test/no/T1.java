package zzjjcc.test.no;

import org.junit.jupiter.api.Test;

public class T1 {
    @Test
    public void t1() {
        boolean b = Integer.valueOf(1).equals(null);
        System.out.println(b);
    }

    @Test
    public void t2() {
        System.out.println(m1(null));
    }

    public boolean m1(Integer i) {
        switch (i) {
            case 1:
            case 10:
                return true;
            case 0:
                return false;
            default:
                return m1(i + 1);
        }
    }
}
