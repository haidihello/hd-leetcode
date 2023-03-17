package com.leetcode.editor.java.daili.statisproxy;

/**
 * 实际要被代理的对象
 * @Author: HaiDi
 * @Date: 2023/3/17 13:49
 */
public class House implements Subject {
    @Override
    public void job() {
        System.out.println("我是客户想要的房子，通过 job 方法注册在数据库中");
    }
}
