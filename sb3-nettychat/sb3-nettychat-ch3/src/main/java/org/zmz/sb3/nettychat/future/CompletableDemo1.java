package org.zmz.sb3.nettychat.future;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CompletableDemo1 {

    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            32,
            64,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableDemo1 cfDemo1 = new CompletableDemo1();
        long start = System.currentTimeMillis();
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(cfDemo1::task1, poolExecutor);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(cfDemo1::task2, poolExecutor);
        CompletableFuture.allOf(cf1, cf2);
        String s1 = cf1.get();
        String s2 = cf2.get();
        long end = System.currentTimeMillis();
        //同步调用
        //cfDemo1.task1();
        //cfDemo1.task2();
        log.info("{} , {}", s1, s2);
        log.info("耗时: {}", end - start);

    }

    public String task1() {
        delay(1);
        log.info("task1--------------()");
        return "task1";
    }

    public String task2() {
        delay(2);
        log.info("task2===============()");
        return "task2";
    }

    public void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @Setter
    @Builder
    static class Response {
        private String s1;
        private String s2;
    }
}
