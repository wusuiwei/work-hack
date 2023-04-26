package com.wusuiwei.java.spring6.junit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/* 不常用操作*/
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:bean.xml")

/*常用操作*/
@SpringJUnitConfig(locations = "classpath:bean.xml")
public class UserJunit5Test {
    @Autowired
    User user;

    @Test
    void test() {
        user.run();
    }
}
