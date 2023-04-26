package com.wusuiwei.java.spring6.principle.service.impl;

import com.wusuiwei.java.spring6.principle.annotation.Bean;
import com.wusuiwei.java.spring6.principle.annotation.Di;
import com.wusuiwei.java.spring6.principle.dao.UserDao;
import com.wusuiwei.java.spring6.principle.service.UserService;

@Bean
public class UserServiceImpl implements UserService {
    @Di
    UserDao userDao;

    @Override
    public void add() {
        System.out.println(userDao.toString());
        System.out.println("add...");
    }
}
