package com.leetcode.editor.java.daili.cjlib;

/**
 * 被代理类
 */
public class RealService {

    public void execute(String flag) {
        System.out.println("Test " + flag + " execute.");
    }

    public void submit(String flag) {
        System.out.println("Test " + flag + " submit.");
    }

}