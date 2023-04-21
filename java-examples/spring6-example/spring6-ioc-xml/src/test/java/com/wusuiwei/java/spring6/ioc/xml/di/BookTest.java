package com.wusuiwei.java.spring6.ioc.xml.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class BookTest {
    @Test
    void testSetter() {
        //加载配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");

        Book bean = context.getBean("book",Book.class);
        System.out.println(bean);
    }
    @Test
    void testConstructor() {
        //加载配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");

        Book bean = context.getBean("book1", Book.class);
        System.out.println(bean);
    }
}