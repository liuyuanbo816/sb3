package org.zmz.sb3.newfeatures.test.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Tester_A {

    @Test
    public void t1() {
        List<String> list = new ArrayList<>();

        List<String> rs = list.stream().map(s -> s + "").toList();
        System.out.println(rs);
    }

    @Test
    public void t2() {
        List<Map> list = genMapList();

        List<String> rs = list.stream().map(m -> MapUtils.getString(m, "k1")).toList();
        System.out.println(rs);
    }

    @Test
    public void t3() {
        String text = "aaaa\"1111";
        // 创建 Pattern 对象
        String regex = "\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        // 创建 Matcher 对象
        Matcher matcher = pattern.matcher(text);
        // 查找匹配项
        if (matcher.find()) {
            // 提取引号之间的内容
            String extractedContent = matcher.group(1);
            System.out.println(extractedContent); // 输出: aaaaa\"11111
        } else {
            System.out.println("没有找到匹配的内容");
        }
    }


    public List<Map> genMapList() {
        return List.of(
                Map.of("k1", "v1"),
                Map.of("k2", 22),
                Map.of("k3", 333L)
        );
    }

}
