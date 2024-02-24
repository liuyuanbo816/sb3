package org.zmz.sb3.rabbitmq.order.service.mq.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.sb3.rabbitmq.order.service.mq.OrderMessageSrv;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
public class OrderMessageSrvTest {

    @Autowired
    OrderMessageSrv orderMessageSrv;

    @Test
    public void setUp() throws IOException, TimeoutException {
        orderMessageSrv.handleOrderDetailMessage();
    }

}
