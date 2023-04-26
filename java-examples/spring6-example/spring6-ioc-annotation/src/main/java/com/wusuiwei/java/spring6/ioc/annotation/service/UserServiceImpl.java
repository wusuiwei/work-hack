package com.wusuiwei.java.spring6.ioc.annotation.service;

import com.wusuiwei.java.spring6.ioc.annotation.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    //注入Dao
//    //第一种方式：属性注入
//    @Autowired
//    private UserDao userDao;
//    //第二种方式：set方法注入
//    private UserDao userDao;
//
//    @Autowired
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
//    //第三种方法：构造方法注入
//    private UserDao userDao;
//
//    @Autowired
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

//    //第四种方法：形参注入
//    private UserDao userDao;
//
//    public void setUserDao(@Autowired UserDao userDao) {
//        this.userDao = userDao;
//    }

//    //第五种方式：只有一个参数的构造函数，不用添加@Autowired注解
//    private UserDao userDao;
//
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

    //第六种方式：@Autowired 注解和@Qualifier注解联合使用
    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;


    @Override
    public void addUserService() {
        System.out.println("Userservice 执行了");
        userDao.addUserDao();
    }
}
