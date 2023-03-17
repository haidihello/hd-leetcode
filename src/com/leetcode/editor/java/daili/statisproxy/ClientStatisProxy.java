package com.leetcode.editor.java.daili.statisproxy;

/**
 * @Author: HaiDi
 * @Date: 2023/3/17 14:28
 */
public class ClientStatisProxy {
    public static void main(String[] args) {
        House house = new House();
        Proxy proxy = new Proxy(house);
        proxy.job();
    }
}
