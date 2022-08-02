package com.leetcode.editor.java.test;

/**
 * 静态类加载问题
 * @author
 */
public class StaticInit {
    public static void main(String[] args) {
//        Student student = new Student();
//        System.out.println(student.c);
        System.out.println(Student.a);
    }

}
class Person{
    public static int a = 10;
    public int c = 5;
    static {
        System.out.println("父类");
    }
}
class Student extends Person{
    public static int b= 1000;
    static {
        System.out.println("子类");
    }
}