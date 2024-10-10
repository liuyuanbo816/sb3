package org.zmz.sb3.rabbitmq.order.dao.cache;

import org.zmz.sb3.rabbitmq.order.po.OrderDetail;

public interface OrderDetailCache {
    void saveOrderDetail(OrderDetail orderDetail);

    void updateOrderDetail(Long id);

    OrderDetail getOrderDetailById(Long id);

    void delOrderDetailById(Long id);
}
