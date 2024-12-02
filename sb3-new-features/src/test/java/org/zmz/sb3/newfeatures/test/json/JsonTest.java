package org.zmz.sb3.newfeatures.test.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonTest {
    public static <T> T getList(Map<String, T> map, String key, T defaultValue) {
        if (map == null) {
            return defaultValue;
        }
        try {
            String object = JSONObject.toJSONString(MapUtils.getObject(map, key));
            return JSONObject.parseObject(object, new TypeReference<T>() {
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return defaultValue;
    }

    public static <T, C> C getList(Map<String, T> map, String key, T defaultValue, Class<?> clz) {
        T t = getList(map, key, defaultValue);
        if (t != null && t.getClass().isAssignableFrom(clz)) {
            return (C) t;
        }
        return null;
    }

    @Test
    public void t1() {
        String json1 = """
                {
                    "sysUserNames":["银行股","大法官","外服务"],
                    "id":1001
                }
                """;
        Map<String, Object> map = JSONObject.parseObject(json1, new TypeReference<Map<String, Object>>() {
        });

        List<String> sysUserNames = (List<String>) getList(map, "sysUserNames", new ArrayList<>());
        System.out.println(sysUserNames);
    }
}
