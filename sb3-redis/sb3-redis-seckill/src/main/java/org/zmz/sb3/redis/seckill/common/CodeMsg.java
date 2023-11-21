package org.zmz.sb3.redis.seckill.common;

import lombok.Getter;

@Getter
public enum CodeMsg {
    SUCCESS(0, "SUCCESS"),
    FAIL(500100, "SERVER_ERROR");
    //登录模块 5002xx
    //商品模块 5003xx
    //订单模块 5004xx
    private final Integer code;
    private final String msg;

    CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
