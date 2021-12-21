package com.leetcode.editor.cn;

/**
 * @Author: HaiDi
 * @Date: 2021/11/23 20:14
 */
public class SingleObject {
    //创建一个对象
    private static SingleObject instance = new SingleObject();

    //构造函数私有化
    private SingleObject() {

    }

    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World");

    }
}
