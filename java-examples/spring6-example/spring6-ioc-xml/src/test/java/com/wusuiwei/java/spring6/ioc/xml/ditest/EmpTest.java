package com.wusuiwei.java.spring6.ioc.xml.ditest;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class EmpTest {
    /**
     * 测试对象注入：引入外部bean
     */
    @Test
    void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("baen-ditest.xml");

        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }
    /**
     * 测试对象注入：内部bean
     */
    @Test
    void test2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("baen-ditest.xml");

        Emp emp = context.getBean("emp2", Emp.class);
        System.out.println(emp);
    }
    /**
     * 测试对象注入：级联属性赋值
     */
    @Test
    void test3() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("baen-ditest.xml");

        Emp emp = context.getBean("emp3", Emp.class);
        System.out.println(emp);
    }

    /**
     * 测试注入数组
     */
    @Test
    void test4() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("baen-diarray.xml");

        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }


    /**
     * 测试注入List
     */
    @Test
    void test5() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-dilist.xml");

        Dept dept = context.getBean("dept", Dept.class);
        System.out.println(dept);
    }

}