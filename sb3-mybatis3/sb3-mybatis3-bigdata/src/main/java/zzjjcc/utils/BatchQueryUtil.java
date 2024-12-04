package zzjjcc.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
public class BatchQueryUtil<T, R> {

    private final ExecutorService executorService;
    private final int batchSize;

    public BatchQueryUtil(int batchSize, int numberOfThreads, ExecutorService executor) {
        this.batchSize = batchSize;
        this.executorService = Objects.requireNonNullElseGet(executor, () -> Executors.newFixedThreadPool(numberOfThreads));
    }

    public BatchQueryUtil(int batchSize, int numberOfThreads) {
        this(batchSize, numberOfThreads, null);
    }

    public BatchQueryUtil(int batchSize, ExecutorService executorService) {
        if (executorService == null) {
            throw new IllegalArgumentException("executorService cannot be null");
        }
        this.batchSize = batchSize;
        this.executorService = executorService;
    }

    public List<R> batchQuery(int totalCount, Function<List<T>, List<R>> queryFunction, List<T> data) {
        List<CompletableFuture<List<R>>> futures = new ArrayList<>();
        int numberOfBatches = (int) Math.ceil((double) totalCount / batchSize);

        for (int i = 0; i < numberOfBatches; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, totalCount);
            List<T> batchData = data.subList(start, end);

            CompletableFuture<List<R>> future = CompletableFuture.supplyAsync(() -> queryFunction.apply(batchData), executorService);
            futures.add(future);
        }

        executorService.shutdown();
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v ->
                        futures.stream()
                                .flatMap(f -> f.join().stream())
                                .toList()).join();
    }

    public static void main(String[] args) {
        // Example usage
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(i);
        }

        BatchQueryUtil<Integer, String> batchQueryUtil = new BatchQueryUtil<>(10, 10);

        Function<List<Integer>, List<String>> queryFunction = (batch) -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<String> results = new ArrayList<>();
            for (Integer num : batch) {
                results.add("Result for " + num);
            }
            log.info("当前线程: {} , 处理结果: {}", Thread.currentThread().getName(), results);
            return results;
        };

        long start = System.currentTimeMillis();
        List<String> rs = batchQueryUtil.batchQuery(100, queryFunction, data);
        long end = System.currentTimeMillis();
        log.info("耗时: {}", end - start);
        log.info("结果: {}", rs);
    }
}