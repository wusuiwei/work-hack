package com.wusuiwei.java.spring6.ioc.xml.auto.controller;

import com.wusuiwei.java.spring6.ioc.xml.auto.service.UserServiceImpl;
import com.wusuiwei.java.spring6.ioc.xml.auto.service.Userservice;

public class UserController {

    private Userservice userService;

    public Userservice getUserService() {
        return userService;
    }

    public void setUserService(Userservice userService) {
        this.userService = userService;
    }

    public void addUser() {
        System.out.println("UserController 执行了");
        userService.addUserService();
    }

}
