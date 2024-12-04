package org.zmz.sb3.redis.seckill.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zmz.sb3.redis.seckill.domain.Stock;

@Service
@Slf4j
public class StockService {

    Stock stock = new Stock(5000);

    public void deduce() {
        int count = stock.getCount();
        count--;
        stock.setCount(count);
        log.info("剩余库存: {}", stock.getCount());
    }
}
