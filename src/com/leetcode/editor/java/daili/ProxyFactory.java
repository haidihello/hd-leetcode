package com.leetcode.editor.java.daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于JDK的动态代理
 * 动态代理类
 */
public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance() {
        System.out.println("----target class-----" + target.getClass());
        System.out.println("----target interfaces---" + target.getClass().getInterfaces());
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("----开始事务2---");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
//                        System.out.println("----提交事务2----");
                        return returnValue;
                    }
                }
        );
    }
}
