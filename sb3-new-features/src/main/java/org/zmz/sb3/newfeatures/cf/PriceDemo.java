package org.zmz.sb3.newfeatures.cf;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class PriceDemo {

    static final List<Shop> SHOPS = List.of(
            new Shop("shop1"),
            new Shop("shop2"),
            new Shop("shop3"),
            new Shop("shop4"),
            new Shop("shop5"),
            new Shop("shop6"),
            new Shop("shop7"),
            new Shop("shop8"),
            new Shop("shop9"),
            new Shop("shop10"),
            new Shop("shop11"),
            new Shop("shop12"),
            new Shop("shop13"),
            new Shop("shop14"),
            new Shop("shop15"),
            new Shop("shop16"),
            new Shop("shop17")
    );

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            Math.min(SHOPS.size(), 64),
            32,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(32),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardPolicy()
    );

    // 使用并行流
    public List<String> findPricesByParallel(String product) {
        return SHOPS.parallelStream()
                .map(s -> s.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesByCompletable(String product) {
        return SHOPS.stream()
                .map(
                        s -> CompletableFuture.supplyAsync(
                                () -> s.getPrice(product), poolExecutor
                        ))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenComposeAsync(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), poolExecutor))
                )
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .sorted(Comparator.comparingDouble(Discount.DiscountRecord::discountPrice))
                .map(Discount.DiscountRecord::finalShopPrice)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        log.info("core: {}", Runtime.getRuntime().availableProcessors());
        PriceDemo priceDemo = new PriceDemo();
        long start = System.currentTimeMillis();
        //priceDemo.findPricesByParallel("Iphone16").forEach(rs -> log.info("{}", rs));
        priceDemo.findPricesByCompletable("Iphone16").forEach(rs -> log.info("{}", rs));
        log.info("耗时:{}", (System.currentTimeMillis() - start));
    }

}