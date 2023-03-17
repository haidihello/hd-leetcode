package com.leetcode.editor.java.daili.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * https://segmentfault.com/a/1190000040927525
 */
public class ClientTwo {

    public static void main(String[] args) {
        // 保存动态代理类的字节码文件
        System.getProperties().setProperty(
                "sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 创建被代理类
        RealObject realObject = new RealObject();
        // 获取被代理类的类加载器
        ClassLoader classLoader = realObject.getClass()
                .getClassLoader();
        // 获取被代理类实现的接口的Class对象
        Class<?>[] interfaces = realObject.getClass()
                .getInterfaces();
        // 以被代理类作为入参创建InvocationHandler
        InvocationHandler invocationHandler
                = new TestInvocationHandler(realObject);
        // 通过调用Proxy的newProxyInstance()方法创建动态代理对象
        Object proxyInstance = Proxy.newProxyInstance(
                classLoader, interfaces, invocationHandler);

        ((TestServiceA) proxyInstance).executeTestA();
        ((TestServiceA) proxyInstance).submitTestA();
        ((TestServiceB) proxyInstance).executeTestB();
        ((TestServiceB) proxyInstance).submitTestB();
    }

}