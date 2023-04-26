package com.wusuiwei.java.spring6.ioc.xml.auto.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AutoTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-auto.xml");

        UserController userController = context.getBean(UserController.class);
        userController.addUser();
    }

}