package com.wusuiwei.java.spring6.aop.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class CalculatorTest {
    @Test
    void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-xml.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(1, 2);
    }

}