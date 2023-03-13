package com.leetcode.editor.java.test;

/**
 * @Author: HaiDi
 * @Date: 2022/7/13 20:38
 */
public class EqualsTes {
//    public static void main(String[] args) {
//        String s1 = new String("123");
//        String s11 = "123";
//        String s22 = "234";
//        String s2 = new String("hello")+new String("world");
//        String s3 = "1234"+"567";
//        System.out.println(s1.intern());
//        System.out.println(s1.intern() == s1);
//        System.out.println(s2.intern() == s2);
//        System.out.println(s2.intern());
//        System.out.println(s3.intern() == s3);
//    }
public static void main(String[] args) {
    int a = 999999999 * 100;
    long b = 10;
    int c = 888888888*100;
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    System.out.println(a>c);
    System.out.println(b>c);
    System.out.println(Integer.MAX_VALUE);
    System.out.println(a<b);
    System.out.println(Integer.MAX_VALUE+1);
    System.out.println(Integer.MIN_VALUE-1);
    System.out.println("二进制"+Integer.toBinaryString(a));
    System.out.println("二进制"+Integer.toBinaryString(c));
    Integer.valueOf(2);




}
}
