package com.wusuiwei.java.spring6.ioc.annotation.controller;


import com.wusuiwei.java.spring6.ioc.annotation.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    //@Resource注解注入bean
    //总结：默认根据byName注入，没有指定name时把属性名当作name，根据name找不到时，才会根据type注入。
    //1. 通过名称进行注入
    @Resource(name = "userService")
    private UserService userService;
//
//    //2. 不指定名字，根据属性名字进行匹配
//    @Resource
//    private UserService userservice;
//
//    //3. 名字匹配不到，通过类型进行注入
//    @Resource
//    private UserService userservice;
//

    public void addUser() {
        System.out.println("UserController 执行了");
        userService.addUserService();
    }

}
