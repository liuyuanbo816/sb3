package org.zmz.sb3.newfeatures.j8;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
public class PriceDemo {

    private static final List<Shop> shops = List.of(
            new Shop("shop01"),
            new Shop("shop02"),
            new Shop("shop03"),
            new Shop("shop03"),
            new Shop("shop04"),
            new Shop("shop05"),
            new Shop("shop06"),
            new Shop("shop07"),
            new Shop("shop08"),
            new Shop("shop09"),
            new Shop("shop10"),
            new Shop("shop11"),
            new Shop("shop12"),
            new Shop("shop13"),
            new Shop("shop14"),
            new Shop("shop15"),
            new Shop("shop16"),
            new Shop("shop17"),
            new Shop("shop18"),
            new Shop("shop19"),
            new Shop("shop20"),
            new Shop("shop21"),
            new Shop("shop22"),
            new Shop("shop23"),
            new Shop("shop24"),
            new Shop("shop25"),
            new Shop("shop26"),
            new Shop("shop27"),
            new Shop("shop28"),
            new Shop("shop29"),
            new Shop("shop30"),
            new Shop("shop31"),
            new Shop("shop32")
    );

    public List<String> findPricesByParallel(String product) {
        return shops.stream().parallel()
                .map(s -> String.format("%s 的商品价格是 %.2f", s.getName(), s.getPrice()))
                .peek(s -> log.info("{}", s))
                .collect(Collectors.toList());
    }

    public List<String> findPricesByFuture(String product) {
        return shops.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> String.format("%s 的商品价格是 %.2f", s.getName(), s.getPrice())))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .peek(s -> log.info("{}", s))
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        int core = Runtime.getRuntime().availableProcessors();
        log.info("核数: {}", core);
        PriceDemo pd = new PriceDemo();
        long start = System.currentTimeMillis();
        //pd.findPricesByParallel("Iphone15");
        pd.findPricesByFuture("Iphone15");
        long end = System.currentTimeMillis();
        log.info("耗时: {}", end - start);
    }
}
