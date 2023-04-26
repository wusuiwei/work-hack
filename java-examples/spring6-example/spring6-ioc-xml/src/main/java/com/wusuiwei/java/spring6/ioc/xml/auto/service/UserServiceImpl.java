package com.wusuiwei.java.spring6.ioc.xml.auto.service;

import com.wusuiwei.java.spring6.ioc.xml.auto.dao.UserDao;

public class UserServiceImpl implements Userservice {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUserService() {
        System.out.println("Userservice 执行了");
        userDao.addUserDao();
    }
}
