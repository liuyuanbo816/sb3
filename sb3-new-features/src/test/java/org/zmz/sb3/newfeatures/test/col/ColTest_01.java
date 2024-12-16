package org.zmz.sb3.newfeatures.test.col;

import org.junit.jupiter.api.Test;
import org.zmz.sb3.newfeatures.col.FastListUtil;

import java.util.List;
import java.util.Map;

public class ColTest_01 {
    @Test
    public void t1() {
        List<Map<String, Object>> list = List.of(
                Map.of("k1", List.of("1", "2")),
                Map.of("k2", new Object()),
                Map.of("k3", 1),
                Map.of("k4", new Person("zjc", 26))
        );

        if (FastListUtil.isNotEmpty(list)) {
            for (Map<String, Object> map : list) {
                FastListUtil.getValue(map, "k1", List.class);
            }
        }
    }

    record Person(String name, Integer age) {

    }
}
