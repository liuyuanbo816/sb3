package org.zmz.sb3.rabbitmq.order.dao.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.zmz.sb3.rabbitmq.order.dao.cache.OrderDetailCache;
import org.zmz.sb3.rabbitmq.order.po.OrderDetail;
import org.zmz.sb3.rabbitmq.order.support.JsonSupport;

import static org.zmz.sb3.rabbitmq.order.dao.CacheConstants.ORDER_DETAIL_CACHE_KEY;

@Repository
public class OrderDetailCacheImpl implements OrderDetailCache {

    @Autowired
    RedisTemplate<String, Object> orderRedisTemplate;

    @Autowired
    JsonSupport jsonSupport;

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        String jsonStr = jsonSupport.obj2Str(orderDetail);
        orderRedisTemplate
                .boundHashOps(ORDER_DETAIL_CACHE_KEY)
                .put(String.valueOf(orderDetail.getId()), jsonStr);
    }

    @Override
    public void updateOrderDetail(Long id) {
        OrderDetail orderDetail = this.getOrderDetailById(id);
        if (orderDetail != null) {
            this.delOrderDetailById(id);
            this.saveOrderDetail(orderDetail);
        }
    }

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        if (orderRedisTemplate.boundHashOps(ORDER_DETAIL_CACHE_KEY).get(String.valueOf(id)) instanceof String s) {
            return jsonSupport.str2Obj(s, OrderDetail.class);
        }
        return null;
    }

    @Override
    public void delOrderDetailById(Long id) {
        orderRedisTemplate
                .boundHashOps(ORDER_DETAIL_CACHE_KEY)
                .delete(String.valueOf(id));
    }
}
