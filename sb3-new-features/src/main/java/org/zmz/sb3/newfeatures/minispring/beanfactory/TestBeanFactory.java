package org.zmz.sb3.newfeatures.minispring.beanfactory;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;

@Slf4j
public class TestBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);
        scanner.scan(TestBeanFactory.class.getPackageName());
        MyBean myBean = beanFactory.getBean(MyBean.class);
        log.info("{}", myBean);
    }

    @Component
    @ToString
    static class MyBean {

    }
}
