package org.zmz.sb3.rabbitmq.order.dto;

import lombok.Getter;
import lombok.Setter;
import org.zmz.sb3.rabbitmq.order.enums.OrderMessageStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderMessageDTO {
    private Long orderId;
    private OrderMessageStatus orderMessageStatus;
    private BigDecimal price;
    private Long deliverymanId;
    private Long accountId;
    private Long productId;
    private Long settlementId;
    private Long rewardId;
    //奖励数量
    private Integer rewardAmount;
    private Boolean confirmed;
}
