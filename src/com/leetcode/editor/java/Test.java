package com.leetcode.editor.java;

/**
 * @Author: HaiDi
 * @Date: 2022/3/25 11:40
 */
public class Test {
    @org.junit.Test
    public void tt(){
        System.out.println(TT.a);
    }
}

class TT {
    static {
        System.out.println("TT 初始化");
    }

    public static int a = 1;
    public static final int b = 1;

}
