package org.zmz.sb3.newfeatures.test.algorithm;

import lombok.*;

import java.util.*;

public class Algorithm1 {

    public static void main(String[] args) {
        List<String> list = List.of("00:00", "05:09", "23:59", "14:30", "12:00", "20:58");
        List<String> list2 = List.of("00:01", "00:02", "00:05", "00:04", "00:08", "00:10");
        Map<Integer, String> minutes = convert(list);
        Map<Integer, String> minutes2 = convert(list2);
        System.out.println(minutes);
        System.out.println("========================");
        List<InnerPair> test1 = test(minutes, 1440);
        System.out.println(test1);
        System.out.println("========================");
        System.out.println(minutes2);
        System.out.println("========================");
        List<InnerPair> test2 = test(minutes2, 5);
        System.out.println(test2);
    }

    public static Map<Integer, String> convert(List<String> list) {
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

    private static boolean calcDiff(Integer a, Integer b, Integer diff) {
        return Math.abs(a - b) > diff;
    }

    private static List<InnerPair> test(Map<Integer, String> map, Integer targetDiff) {
        List<Integer> list = new ArrayList<>(map.keySet());
        List<InnerPair> pairs = new ArrayList<>(list.size());
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Integer a = list.get(i);
                Integer b = list.get(j);
                if (calcDiff(a, b, targetDiff)) {
                    InnerPair pair = new InnerPair(map.get(a), map.get(b));
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
    static class InnerPair {
        private String left;
        private String right;

        @Override
        public String toString() {
            return "(" + left + " , " + right + ")";
        }
    }

}
