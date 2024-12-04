package org.zmz.sb3.redis.seckill.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Stock {
    private int count;

    public Stock(int count) {
        this.count = count;
    }
}
