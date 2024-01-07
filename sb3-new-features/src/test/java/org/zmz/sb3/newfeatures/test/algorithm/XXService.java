package org.zmz.sb3.newfeatures.test.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class XXService {
    public Map<Integer, String> convert(List<String> list) {
        Map<Integer, String> res = new LinkedHashMap<>(list.size());
        for (String s : list) {
            String[] items = s.split(":");
            int hour = Integer.parseInt(items[0]);
            int minute = Integer.parseInt(items[1]);

            Integer totalMinute = hour * 60 + minute;
            res.put(totalMinute, s);
        }
        return res;
    }

    public boolean calcDiff(Integer a, Integer b, Integer diff) {
        return Math.abs(a - b) > diff;
    }

    public List<XXInnerPair> test(Map<Integer, String> map, Integer targetDiff) {
        List<Integer> list = new ArrayList<>(map.keySet());
        List<XXInnerPair> pairs = new ArrayList<>(list.size());
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Integer a = list.get(i);
                Integer b = list.get(j);
                if (calcDiff(a, b, targetDiff)) {
                    XXInnerPair pair = new XXInnerPair(map.get(a), map.get(b));
                    pairs.add(pair);
                }
            }
        }

        return pairs;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class XXInnerPair {
        private String left;
        private String right;

        @Override
        public String toString() {
            return "(" + left + " , " + right + ")";
        }
    }
}
