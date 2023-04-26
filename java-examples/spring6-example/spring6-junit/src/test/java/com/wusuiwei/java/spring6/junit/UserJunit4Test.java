package com.wusuiwei.java.spring6.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class UserJunit4Test {
    @Autowired
    User user;

    @Test
    public  void test() {
        user.run();
    }
}
