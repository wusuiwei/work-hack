package com.wusuiwei.java.spring6.ioc.annotation.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void addUserDao() {
        System.out.println("UserDao执行了");
    }
}
