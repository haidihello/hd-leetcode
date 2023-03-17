package com.leetcode.editor.java.daili.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvocationHandler implements InvocationHandler {

    private Object realObject;

    public TestInvocationHandler(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invokeResult = method.invoke(realObject, args);
        after();
        return invokeResult;
    }

    private void before() {
        System.out.println("Begin to do.");
    }

    private void after() {
        System.out.println("Finish to do.");
    }

}