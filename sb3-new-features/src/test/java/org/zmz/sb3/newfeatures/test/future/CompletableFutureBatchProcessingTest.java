package org.zmz.sb3.newfeatures.test.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class CompletableFutureBatchProcessingTest {

    @Test
    public void testCompletableFutureBatchProcessing() throws ExecutionException, InterruptedException {
        // 假设我们有一组数据
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 定义批次大小
        int batchSize = 3;

        // 将数据分成批次
        List<List<Integer>> batches = partition(numbers, batchSize);

        long start = System.currentTimeMillis();

        // 使用 CompletableFuture 处理每个批次
        List<CompletableFuture<List<Integer>>> futures = batches.stream()
                .map(batch -> CompletableFuture.supplyAsync(() -> processBatch(batch)))
                .toList();

        // 等待所有批次完成，并合并结果
        List<Integer> results = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .flatMap(future -> future.join().stream())
                        .collect(Collectors.toList()))
                .get(); // 获取最终结果

        long end = System.currentTimeMillis();
        log.info("并行处理耗时: {}", end - start);
        // 输出结果
        log.info("并行处理合并结果: {}", results);
    }

    @Test
    public void testCompletableFutureBatchProcessingNo() {
        // 假设我们有一组数据
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 定义批次大小
        int batchSize = 3;

        // 将数据分成批次
        List<List<Integer>> batches = partition(numbers, batchSize);


        long start = System.currentTimeMillis();

        // 使用 CompletableFuture 处理每个批次
        List<List<Integer>> futures = batches.stream()
                .map(CompletableFutureBatchProcessingTest::processBatch)
                .toList();

        // 等待所有批次完成，并合并结果
        List<Integer> results = futures.stream()
                .flatMap(List::stream)
                .toList();// 获取最终结果

        long end = System.currentTimeMillis();
        log.info("串行处理耗时: {}", end - start);
        // 输出结果
        log.info("串行处理合并结果: {}", results);
    }

    // 将列表分成多个批次
    private static List<List<Integer>> partition(List<Integer> list, int batchSize) {
        return IntStream.range(0, (list.size() + batchSize - 1) / batchSize)
                .mapToObj(i -> list.subList(i * batchSize, Math.min(batchSize * (i + 1), list.size())))
                .collect(Collectors.toList());
    }

    // 模拟处理批次的逻辑
    private static List<Integer> processBatch(List<Integer> batch) {
        // 模拟业务处理耗时
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 这里可以是任何处理逻辑，例如计算平方
        return batch.stream()
                .map(num -> num * num) // 示例：计算平方
                .collect(Collectors.toList());
    }
}
