package org.zmz.sb3.redis.seckill.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.zmz.sb3.redis.seckill.domain.Stock;

import java.time.Duration;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class StockService {

    Stock stock = new Stock(5000);

    ReentrantLock lock = new ReentrantLock();

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void deduce() {
        lock.lock();
        stringRedisTemplate.expire("AA", Duration.ofSeconds(100000));
        try {
            int count = stock.getCount();
            count--;
            stock.setCount(count);
            log.info("剩余库存: {}", stock.getCount());
        } finally {
            lock.unlock();
        }
    }
}
