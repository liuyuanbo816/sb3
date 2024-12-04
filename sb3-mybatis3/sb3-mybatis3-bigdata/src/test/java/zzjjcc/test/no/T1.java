package zzjjcc.test.no;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
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

    @Test
    public void t3() {
        String pattern = "[\\u4e00-\\u9fa5a-zA-Z][\\u4e00-\\u9fa5a-zA-Z0-9]{0,6}";
        Pattern p = Pattern.compile(pattern);
        System.out.println(p.matcher("你你你你你你你").matches());
    }

    @Test
    public void t4() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().availableProcessors() << 2);
    }

    public List<String> mockQueryDB(List<Number> ids) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ids.stream().map(i -> "-->" + i).collect(Collectors.toList());
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
