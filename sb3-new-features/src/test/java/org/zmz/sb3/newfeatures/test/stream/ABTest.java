package org.zmz.sb3.newfeatures.test.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ABTest {

    private static final List<A> A_LIST = List.of(
            new A("1", "订单1"),
            new A("2", "订单3"),
            new A("3", "订单3")
    );
    private static final List<B> B_LIST = List.of(
            new B("1", "11", "商品-衣服-蓝色"),
            new B("1", "12", "商品-衣服-黄色"),
            new B("2", "21", "鞋子"),
            new B("2", "22", "帽子"),
            new B("3", "32", "商品-手表")
    );


//    @Test
    public void test1() {
        int count = userFor(A_LIST, B_LIST);
        log.info("循环次数: {}", count);
    }

//    @Test
    public void test2() {
        int count = useStream(A_LIST, B_LIST);
        log.info("循环次数: {}", count);
    }

    @Benchmark
    public void jmh1() {
        int count = userFor(A_LIST, B_LIST);
        log.info("循环次数: {}", count);
    }

    @Benchmark
    public void jmh2() {
        int count = useStream(A_LIST, B_LIST);
        log.info("循环次数: {}", count);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ABTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }

    public static int userFor(List<A> as, List<B> bs) {
        int count = 0;
        for (A a : as) {
            List<B> newBs = new ArrayList<>();
            for (B b : bs) {
                count++;
                if (b.getKey().equals(a.getKey()))
                    newBs.add(b);
            }
            a.setBs(newBs);
        }
        return count;
    }

    public static int useStream(List<A> as, List<B> bs) {
        Map<String, List<B>> map = bs.stream().collect(Collectors.groupingBy(B::getKey));
        int count = map.size();
        for (A a : as) {
            count++;
            List<B> newBs = map.get(a.getKey());
            a.setBs(newBs);
        }
        return count;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    static class A {
        private String key;
        private String orderName;
        private List<B> bs;


        public A(String key, String orderName) {
            this.key = key;
            this.orderName = orderName;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class B {
        private String key;
        private String detail;
        private String desc;
    }

}
