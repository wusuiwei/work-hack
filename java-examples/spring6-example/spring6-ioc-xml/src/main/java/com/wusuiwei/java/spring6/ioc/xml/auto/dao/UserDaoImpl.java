package com.wusuiwei.java.spring6.ioc.xml.auto.dao;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUserDao() {
        System.out.println("UserDao执行了");
    }
}
