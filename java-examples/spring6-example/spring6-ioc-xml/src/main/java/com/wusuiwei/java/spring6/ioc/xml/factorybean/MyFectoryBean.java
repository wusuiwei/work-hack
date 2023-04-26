package com.wusuiwei.java.spring6.ioc.xml.factorybean;

import com.wusuiwei.java.spring6.ioc.xml.life.User;
import org.springframework.beans.factory.FactoryBean;

public class MyFectoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
