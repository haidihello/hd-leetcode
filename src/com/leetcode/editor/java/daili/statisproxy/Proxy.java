package com.leetcode.editor.java.daili.statisproxy;

/**
 * @Author: HaiDi
 * @Date: 2023/3/17 13:50
 */
public class Proxy implements Subject {
    private House house;

    public Proxy(House house) {
        this.house = house;
    }

    @Override
    public void job() {
        System.out.println("我是中介，我会在数据库中检索，帮助客户找到心仪的房子");
        house.job();
        System.out.println("我是中介，找到了数据库中符合客户需求的方法");
    }
}
