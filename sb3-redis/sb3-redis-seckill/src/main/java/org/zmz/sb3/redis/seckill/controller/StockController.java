package org.zmz.sb3.redis.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.sb3.redis.seckill.common.R;
import org.zmz.sb3.redis.seckill.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;

    @Autowired
    public void getStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/deduce")
    public R<String> deduce() {
        stockService.deduce();
        return R.success("扣减库存成功");
    }

}
