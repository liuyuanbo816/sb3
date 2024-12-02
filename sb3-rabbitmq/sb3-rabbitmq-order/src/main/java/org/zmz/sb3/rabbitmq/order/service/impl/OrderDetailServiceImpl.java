package org.zmz.sb3.rabbitmq.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zmz.sb3.rabbitmq.order.dao.cache.OrderDetailCache;
import org.zmz.sb3.rabbitmq.order.po.OrderDetail;
import org.zmz.sb3.rabbitmq.order.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailCache orderDetailCacheImpl;

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailCacheImpl.saveOrderDetail(orderDetail);
    }

    @Override
    public void updateOrderDetail(Long id) {
        orderDetailCacheImpl.updateOrderDetail(id);
    }

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        return orderDetailCacheImpl.getOrderDetailById(id);
    }

    @Override
    public void delOrderDetailById(Long id) {
        orderDetailCacheImpl.delOrderDetailById(id);
    }
}
