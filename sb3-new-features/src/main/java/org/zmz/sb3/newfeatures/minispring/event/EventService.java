package org.zmz.sb3.newfeatures.minispring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventService {

    @Autowired
    ApplicationContext ctx;

    public void register(String reqNo, String type) {
        Event event = new Event();
        event.setRequestNo(reqNo);
        event.setType(Event.EventEnum.valueOf(type));
        log.info("当前线程 : {} , 发布主流程无关事件", Thread.currentThread());
        ctx.publishEvent(event);
    }

    @Async
    @EventListener(condition = "#event.type==T(org.zmz.sb3.newfeatures.minispring.event.Event.EventEnum).SMS")
    public void sendSms(Event event) {
        log.info("当前线程: {} , 发送短信: {}", Thread.currentThread(), event.getRequestNo());
    }

    @Async
    @EventListener(condition = "#event.type==T(org.zmz.sb3.newfeatures.minispring.event.Event.EventEnum).EMAIL")
    public void sendEmail(Event event) {
        log.info("当前线程: {} , 发送邮件: {}", Thread.currentThread(), event.getRequestNo());
    }

}
