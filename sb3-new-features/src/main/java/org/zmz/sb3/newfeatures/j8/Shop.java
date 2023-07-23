package org.zmz.sb3.newfeatures.j8;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@Getter
@Setter
public class Shop {
    static ThreadLocalRandom random = ThreadLocalRandom.current();
    String name;

    public Shop(String name) {
        this.name = name;
    }

    public void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getPrice() {
        delay();
        return random.nextDouble() * 100;
    }

    public Future<Double> getPriceAsync() {
//        CompletableFuture<Double> cf = new CompletableFuture<>();
//        new Thread(() -> cf.complete(getPrice())).start();
        return CompletableFuture.supplyAsync(this::getPrice);
        // return random.nextDouble() * 100;
    }

    public static void main(String[] args) throws Exception {
        Shop shop = new Shop("苹果商店");
        long start = System.currentTimeMillis();
        Future<Double> priceAsync = shop.getPriceAsync();
        long end = System.currentTimeMillis();
        log.info("耗时: {}", end - start);
        Double rs = priceAsync.get();
        log.info("价格: {}", rs);
    }
}
