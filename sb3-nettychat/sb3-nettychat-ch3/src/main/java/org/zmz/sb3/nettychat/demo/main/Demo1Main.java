package org.zmz.sb3.nettychat.demo.main;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.zmz.sb3.nettychat.demo.Demo1Controller;

public class Demo1Main {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);
        scanner.scan("org.zmz.sb3.nettychat.demo");

        Demo1Controller demo1Controller = beanFactory.getBean(Demo1Controller.class);
        demo1Controller.m1();
    }
}
