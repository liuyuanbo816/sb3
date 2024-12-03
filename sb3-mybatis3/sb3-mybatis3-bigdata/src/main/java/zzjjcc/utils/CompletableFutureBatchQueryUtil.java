package zzjjcc.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public final class CompletableFutureBatchQueryUtil {
    private CompletableFutureBatchQueryUtil() {

    }

    public static <R> List<R> batchQuery(List<? extends Number> list, int batchSize, Function<List<? extends Number>, List<R>> func, Executor executor) {
        List<CompletableFuture<List<R>>> futures = new ArrayList<>();

        int cap = list.size();
        if (cap <= batchSize) {
            return func.apply(list);
        }
        AtomicInteger idx = new AtomicInteger(0);
        for (int i = 0; i < cap; i += batchSize) {
            idx.getAndAdd(i);
            // 创建异步任务
            CompletableFuture<List<R>> future = CompletableFuture.supplyAsync(() -> {
                List<R> rs = func.apply(list.subList(idx.get(), idx.get() + batchSize));
                log.info(">>>批次处理结果>>>:{}", rs);
                return rs;
            }, executor);
            futures.add(future);
        }

        // 等待所有异步任务完成并合并结果
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .flatMap(future -> future.join().stream())
                        .collect(Collectors.toList()))
                .join();
    }
}