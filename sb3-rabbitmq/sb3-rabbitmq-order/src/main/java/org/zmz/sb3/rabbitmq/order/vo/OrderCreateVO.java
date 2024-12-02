package org.zmz.sb3.rabbitmq.order.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateVO {
    //用户id
    private Long accountId;
    //地址
    private String address;
    //产品id
    private Long productId;
}
