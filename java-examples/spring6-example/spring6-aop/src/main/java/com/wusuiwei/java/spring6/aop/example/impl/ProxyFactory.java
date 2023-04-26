package com.wusuiwei.java.spring6.aop.example.impl;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //返回代理对象
    public Object getProxy() {
        /**
         * Proxy.newProxyInstance()方法
         * 三个参数：
         * 1. ClassLoader classLoader：加载动态生成代理类的类加载器
         * 2. Class<?>[] interfaces：目标对象实现的所有接口的Class类型数组
         * 3. InvocationHandler invocationHandler: 设置代理对象实现目标对象的过程
         */
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] classes = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {
            //1. proxy: 代理对象
            //2. 需要重写目标对象的方法
            //3. method方法的参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("[动态代理][日志] "+method.getName()+"，参数："+ Arrays.toString(args));
                //调用目标方法
                Object result = method.invoke(target, args);
                System.out.println("[动态代理][日志] "+method.getName()+"，结果："+ result);
                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader, classes, invocationHandler);
    }
}
