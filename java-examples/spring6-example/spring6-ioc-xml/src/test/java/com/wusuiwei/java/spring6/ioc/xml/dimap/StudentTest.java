package com.wusuiwei.java.spring6.ioc.xml.dimap;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    /**
     * 测试map
     */
    @Test
    void test() {
        //加载配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-dimap.xml");

        Student student = context.getBean("student", Student.class);
        Teacher teacher = context.getBean("teacher", Teacher.class);

        System.out.println(student);
        System.out.println(teacher);
    }

    /**
     * 测试util:map util:list
     */
    @Test
    void test2() {
        //加载配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-dicollection.xml");

        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }

    /**
     * 测试 p 命名空间
     */
    @Test
    void test3() {
        //加载配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-dicollection.xml");

        Student student = context.getBean("studentP", Student.class);
        System.out.println(student);
    }

}