package com.wusuiwei.java.spring6.aop.example;

import com.wusuiwei.java.spring6.aop.example.impl.CalculatorImpl;
import com.wusuiwei.java.spring6.aop.example.impl.ProxyFactory;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    void test() {
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.add(1, 2);
    }
}