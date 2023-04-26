package com.wusuiwei.java.spring6.aop.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class CalculatorTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-aop.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(1, 2);
    }

}