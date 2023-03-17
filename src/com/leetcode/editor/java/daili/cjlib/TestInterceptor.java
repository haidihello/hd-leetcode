package com.leetcode.editor.java.daili.cjlib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestInterceptor implements MethodInterceptor {

    /**
     * @param o 代理对象
     * @param method 被代理对象的方法
     * @param objects 被代理对象的方法参数类型
     * @param methodProxy 被代理对象的方法的代理
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println("Begin to do.");
    }

    private void after() {
        System.out.println("Finish to do.");
    }

}