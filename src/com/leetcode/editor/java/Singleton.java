package com.leetcode.editor.java;

/**
 * 手写单例
 * @Author: HaiDi
 * @Date: 2023/4/17 10:19
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton(){
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}

/**
 * 单例懒汉式
 */
 class Singleton2 {
    private static Singleton2 singleton2;
    private Singleton2(){
    }

    public static Singleton2 getSingleton() {
        if(singleton2==null){
            singleton2 = new Singleton2();

        }

        return singleton2;
    }
}
/**
 * 双重检查创建单例
 */
class Singleton3 {
    private static Singleton3 singleton3;
    private Singleton3(){
    }
    public static Singleton3 getSingleton() {
        if (singleton3 == null) {
            synchronized (Singleton3.class) {
                if(singleton3==null){
                    singleton3 = new Singleton3();

                }
            }
        }
        return singleton3;
    }
}
