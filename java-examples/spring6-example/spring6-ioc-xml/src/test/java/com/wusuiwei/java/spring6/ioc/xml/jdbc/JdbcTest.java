package com.wusuiwei.java.spring6.ioc.xml.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTest {

    @Test
    void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbc.xml");
        DruidDataSource datasource = context.getBean("datasource", DruidDataSource.class);
        System.out.println(datasource.getUrl());
    }
}
