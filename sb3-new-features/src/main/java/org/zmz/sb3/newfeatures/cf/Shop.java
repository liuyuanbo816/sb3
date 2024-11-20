package org.zmz.sb3.newfeatures.cf;

import cn.hutool.core.util.RandomUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@ToString
@Getter
@Setter
public class Shop {

    private String name;

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

    public String getPrice(String product) {
        delay();
        double price = RandomUtil.randomDouble() * 100;
        int len = Discount.values().length;
        // 模拟随机折扣
        Discount percent = Discount.values()[RandomUtil.randomInt(len)];
        return String.format("%s:%s:%.2f:%s", product, this.getName(), price, percent);
    }

    public Future<String> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> getPrice(product));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Shop shop = new Shop("bestShop");
        long start = System.currentTimeMillis();
        Future<String> future = shop.getPriceAsync("Iphone16");
        log.info("调用返回 , 耗时: {}", (System.currentTimeMillis() - start));
        String price = future.get();
        log.info("价格返回 , 耗时: {}", (System.currentTimeMillis() - start));
    }
}