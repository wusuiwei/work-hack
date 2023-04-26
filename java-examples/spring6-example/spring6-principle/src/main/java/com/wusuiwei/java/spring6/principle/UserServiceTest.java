package com.wusuiwei.java.spring6.principle;

import com.wusuiwei.java.spring6.principle.bean.AnnotationApplicationContext;
import com.wusuiwei.java.spring6.principle.bean.ApplicationContext;
import com.wusuiwei.java.spring6.principle.service.UserService;

public class UserServiceTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationApplicationContext("com.wusuiwei.java.spring6.principle");
        UserService userService = (UserService) context.getBean(UserService.class);
        userService.add();
    }
}
