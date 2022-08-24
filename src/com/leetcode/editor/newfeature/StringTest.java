package com.leetcode.editor.newfeature;

public class StringTest {
    public static void main(String[] args) {
//        String s1 = new String("a") + new String("b");
//        String s2 = s1.intern();
//        System.out.println(s1=="ab");
//        System.out.println(s2=="ab");
//        System.out.println("=============");
//        String x = "ab";
//        String ss1 = new String("a") + new String("b");
//        String ss2 = ss1.intern();
//        System.out.println(ss1==x);
//        System.out.println(ss2==x);

        int n = 10;
        double d = n / 0.00;
        double d1 = 0.0;
        double d2 = d1 / 0.0;
        System.out.println(d2);
        System.out.println(d);

        float f = 0.0f;
        float f2 = f / 0.0f;
        System.out.println(10/f);
        //main方法

    }

}
