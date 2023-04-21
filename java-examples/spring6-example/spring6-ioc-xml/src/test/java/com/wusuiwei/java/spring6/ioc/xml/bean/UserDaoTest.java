package com.wusuiwei.java.spring6.ioc.xml.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UserDaoTest {
    @Test
    void test() {
        //加载配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //根据类型获取接口对应bean
        //异常：org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.wusuiwei.java.spring6.ioc.xml.bean.UserDao' available: expected single matching bean but found 2: userDao,personDao
        UserDao bean = context.getBean(UserDao.class);
        System.out.println(bean);
    }

}