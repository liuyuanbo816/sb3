package org.zmz.sb3.vertx.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {
    @Test
    public void t1() {
        Map<Long, Integer> map = new HashMap<>();

        List<Long> ids = List.of(1L, 2L, 1L, 3L, 2L, 3L, 4L);

        for (Long id : ids) {
            map.merge(id, 1, Integer::sum);
        }

        System.out.println(map);
    }
}
