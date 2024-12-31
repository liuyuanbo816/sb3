package io.jcz.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class JMHSample_39_MemoryAccess {
    public static final int N = 20_000_000;

    /*
     * This example highlights the pitfall of accidentally measuring memory access instead of processing time.
     *
     * An int array has got a different memory layout than an ArrayList of boxed ints.
     * This can lead to useless results because the memory access is completely different.
     * Arrays save all their ints in one block on the heap while ArrayLists don't.
     * They save only references to the boxed ints in one block.
     * All the references point to the boxed ints which are usually spread all over the heap.
     * This leads to many cache misses with a big error:
     *
     * Benchmark                                       Mode  Cnt    Score   Error  Units
     * JMHSample_39_MemoryAccess.sumArray              avgt   25    4.887 ± 0.008  ms/op
     * JMHSample_39_MemoryAccess.sumArrayList          avgt   25   35.765 ± 0.112  ms/op
     * JMHSample_39_MemoryAccess.sumArrayListShuffled  avgt   25  169.301 ± 1.064  ms/op
     *
     * The Java Object Layout (JOL) is a tool with which the different memory layouts of arrays and ArrayLists can be
     * examined in more detail.
     */

    private int[] intArray = new int[N];
    private List<Integer> intList = new ArrayList<>(N);
    private List<Integer> shuffledIntList = new ArrayList<>(N);

    @Setup
    public void setup() {
        Random random = new Random(1234);
        for (int i = 0; i < N; i++) {
            intArray[i] = random.nextInt();
            intList.add(intArray[i]);
            shuffledIntList.add(intArray[i]);
        }
        Collections.shuffle(shuffledIntList);
    }

    @Benchmark
    public long sumArray() {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += intArray[i];
        }
        return sum;
    }

    @Benchmark
    public long sumArrayList() {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += intList.get(i);
        }
        return sum;
    }

    @Benchmark
    public long sumArrayListShuffled() {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += shuffledIntList.get(i);
        }
        return sum;
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_39
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + JMHSample_39_MemoryAccess.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}