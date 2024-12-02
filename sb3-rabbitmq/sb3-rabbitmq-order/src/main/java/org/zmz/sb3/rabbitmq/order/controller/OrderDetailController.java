package org.zmz.sb3.rabbitmq.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.sb3.rabbitmq.order.po.OrderDetail;
import org.zmz.sb3.rabbitmq.order.service.OrderDetailService;
import org.zmz.sb3.rabbitmq.order.support.R;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailServiceImpl;

    @PostMapping("/save")
    public R<?> saveOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailServiceImpl.saveOrderDetail(orderDetail);
        return R.ok();
    }

}
