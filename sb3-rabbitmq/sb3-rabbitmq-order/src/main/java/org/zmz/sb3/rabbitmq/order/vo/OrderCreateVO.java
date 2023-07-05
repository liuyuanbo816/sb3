package org.zmz.sb3.rabbitmq.order.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateVO {
    private Long accountId;
    private String address;
    private Long productId;
}
