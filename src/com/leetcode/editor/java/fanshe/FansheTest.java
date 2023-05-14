package com.leetcode.editor.java.fanshe;

/**
 * @Author: HaiDi
 * @Date: 2023/2/22 18:15
 */
public class FansheTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class object = Class.forName("FansheTest.Dog");
        Object[] a = object.getFields();
        //反射


    }
   static class Dog {
        private String name;
        private  int age;

       public Dog() {
       }
   }
}

