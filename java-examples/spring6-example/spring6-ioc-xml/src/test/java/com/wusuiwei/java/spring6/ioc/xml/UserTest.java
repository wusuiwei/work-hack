package com.wusuiwei.java.spring6.ioc.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    void test() {
        //加载配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //获取对象方式一：根据ID获取对象
        User user1 = (User) context.getBean("user");
        System.out.println(user1);

        //获取对象方式二：根据类型获取对象
        //要求：该类型下的对象只配置了一个，否则要报错：org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.wusuiwei.java.spring6.ioc.xml.User' available: expected single matching bean but found 2: user,user1
//        User user2 = context.getBean(User.class);
//        System.out.println(user2);


        //获取对象方式三：根据ID和类型获取对象
        User user3 = context.getBean("user", User.class);
        System.out.println(user3);
    }
}
