package org.zmz.sb3.rabbitmq.order.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
//@TableName(value = "t_order_detail")
public class OrderDetail {
//    @TableId(type= IdType.AUTO)
    private Long id;
    private String status;
    private String address;
    private Long accountId;
    private Long productId;
    private Long deliverymanId;
    private Long settlementId;
    private Long rewardId;
    private BigDecimal price;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
