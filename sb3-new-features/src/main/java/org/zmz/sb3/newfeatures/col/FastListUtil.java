package org.zmz.sb3.newfeatures.col;

import java.util.List;
import java.util.Map;

public final class FastListUtil {
    private FastListUtil() {
    }

    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }

    public static <T> T getValue(Map<String, Object> map, String key, Class<T> valueClass) {
        Object value = map.get(key);
        if (valueClass.isInstance(value)) {
            return valueClass.cast(value);
        }
        // 或者抛出异常
        return null;
    }
}