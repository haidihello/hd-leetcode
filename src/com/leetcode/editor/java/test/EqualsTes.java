package com.leetcode.editor.java.test;

/**
 * @Author: HaiDi
 * @Date: 2022/7/13 20:38
 */
public class EqualsTes {
    public static void main(String[] args) {
        String s1 = new String("123");
        String s2 = new String("hello")+new String("world");
        String s3 = "1234"+"567";
        System.out.println(s1.intern());
        System.out.println(s1.intern() == s1);
        System.out.println(s2.intern() == s2);
        System.out.println(s2.intern());
        System.out.println(s3.intern() == s3);
    }
}
