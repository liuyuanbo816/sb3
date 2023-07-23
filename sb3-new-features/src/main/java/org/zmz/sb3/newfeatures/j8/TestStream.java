package org.zmz.sb3.newfeatures.j8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;

@Slf4j
public class TestStream {
    public static Long parallel(long n) {
        return LongStream.rangeClosed(0, n).parallel().reduce(0L, Long::sum);
    }

    public static Long sequence(long n) {
        return LongStream.rangeClosed(0, n).reduce(0L, Long::sum);
    }

    public static Long forCircle(long n) {
        long sum = 0;
        for (long i = 0L; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static Long parallelWrong(long n) {
        Counter counter = new Counter();
        LongStream.rangeClosed(0, n).parallel().forEach(counter::add);
        return counter.sum;
    }

    public static double test(int count, long n, Function<Long, Long> func) {
        List<Long> costList = new ArrayList<>(10);
        log.info("============================================");
        for (int i = 0; i < count; i++) {
            long start = System.currentTimeMillis();
            Long rs = func.apply(n);
            long end = System.currentTimeMillis();
            long cost = end - start;
            log.info("第 {} 次 计算结果 {} , 耗时: {}", i, rs, cost);
            costList.add(cost);
        }
        log.info("============================================");
        return costList.stream().mapToLong(Long::longValue).average().orElse(0d);
    }

    public static void main(String[] args) {
        long n = 1_000_000_000;
        int count = 10;
        double sequenceRs = test(count, n, TestStream::sequence);
        log.info("串行平均花费时间: {}", sequenceRs);
        double parallelRs = test(count, n, TestStream::parallel);
        log.info("并行平均花费时间: {}", parallelRs);
        double forCircleRs = test(count, n, TestStream::forCircle);
        log.info("普通for循环平均花费时间: {}", forCircleRs);

        double wrongParallel = test(count, n, TestStream::parallelWrong);
        log.info("错误使用 并行平均花费时间: {}", wrongParallel);
    }

    static class Counter {
        private long sum = 0;

        public void add(long n) {
            sum += n;
        }
    }
}
