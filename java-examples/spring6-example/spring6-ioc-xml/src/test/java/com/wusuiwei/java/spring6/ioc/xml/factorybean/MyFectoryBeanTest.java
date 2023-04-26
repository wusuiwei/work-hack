package com.wusuiwei.java.spring6.ioc.xml.factorybean;

import com.wusuiwei.java.spring6.ioc.xml.life.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MyFectoryBeanTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factorybean.xml");
        User user = context.getBean(User.class);
        System.out.println(user);
    }

}