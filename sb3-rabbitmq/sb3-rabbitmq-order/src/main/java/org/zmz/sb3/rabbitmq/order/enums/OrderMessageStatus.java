package org.zmz.sb3.rabbitmq.order.enums;

import lombok.Getter;

@Getter
public enum OrderMessageStatus {
    //订单创建中
    ORDER_CREATING,
    //餐厅已确认
    RESTAURANT_CONFIRMED,
    //骑手已确认
    DELIVERYMAN_CONFIRMED,
    //积分结算已确认
    SETTLEMENT_CONFIRMED,
    //订单完成
    ORDER_FINISHED,
    //失败
    FAILED
}
