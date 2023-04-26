package com.wusuiwei.java.spring6.ioc.xml.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-scope.xml");
        Order bean = context.getBean(Order.class);
        System.out.println(bean);

        Order order = context.getBean(Order.class);
        System.out.println(order);
    }
}
