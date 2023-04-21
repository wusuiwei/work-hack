package com.wusuiwei.java.spring6.helloworld;

import com.wusuiwei.java.spring6.helloworld.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    void test() {
        //加载配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //获取创建的对象
        User user = (User) context.getBean("user");
        System.out.println(user);

        //使用对象调用方法进行测试
        user.add();
    }
}
