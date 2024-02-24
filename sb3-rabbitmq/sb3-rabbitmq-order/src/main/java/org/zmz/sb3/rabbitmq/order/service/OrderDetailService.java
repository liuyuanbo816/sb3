package org.zmz.sb3.rabbitmq.order.service;

import org.zmz.sb3.rabbitmq.order.po.OrderDetail;

public interface OrderDetailService {
    void saveOrderDetail(OrderDetail orderDetail);

    void updateOrderDetail(Long id);

    OrderDetail getOrderDetailById(Long id);

    void delOrderDetailById(Long id);
}
