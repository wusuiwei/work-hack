package com.wusuiwei.java.spring6.ioc.xml.life;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-life.xml");
        User user = context.getBean(User.class);
        System.out.println("6. bena对象创建完成了，可以使用了 ");
        System.out.println(user);
        context.close();//容器关闭
    }
}
