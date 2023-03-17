package com.leetcode.editor.java.daili.cjlib;

import org.springframework.cglib.proxy.Enhancer;

public class ClientThree {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealService.class);
        enhancer.setCallback(new TestInterceptor());
        Object proxyObject = enhancer.create();

        ((RealService) proxyObject).execute("cglib");
        ((RealService) proxyObject).submit("cglib");
    }

}