package com.leetcode.editor.juc;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DeadLock {
    public static final Semaphore a1 = new Semaphore(1);
    public static String obj1 = "对象1";
    public static final Semaphore a2 = new Semaphore(1);
    public static String obj2 = "对象2";

    public static void main(String[] args) {
        Thread1 a = new Thread1();
        new Thread(a).start();
        Thread2 b = new Thread2();
        new Thread(b).start();
        System.out.println("执行结束"); }


}

class Thread1 implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                if (DeadLock.a1.tryAcquire()) {
                    System.out.println(new Date().toString() + " LockA 锁住 obj1");
                    if (DeadLock.a2.tryAcquire()) {
                        System.out.println(new Date().toString() + " LockA 锁住 obj2");
                        Thread.sleep(60 * 1000); // do something
                    } else {
                        System.out.println(new Date().toString() + "LockA 锁 obj2 失败");
                    }
                } else {
                    System.out.println(new Date().toString() + "LockA 锁 obj1 失败");
                }
                DeadLock.a1.release(); // 释放
                DeadLock.a2.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class Thread2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println( new Date().toString() + " LockB 开始执行");
            while (true) {
                if (DeadLock.a2.tryAcquire()) {
                    System.out.println(new Date().toString() + " LockB 锁住 obj2");
                    if (DeadLock.a1.tryAcquire()) {
                        System.out.println(new Date().toString() + " LockB 锁住 obj1");
                        Thread.sleep(60 * 1000); // do something
                    } else {
                        System.out.println(new Date().toString() + "LockB 锁 obj1 失败");
                    }
                } else {
                    System.out.println(new Date().toString() + "LockB 锁 obj2 失败");
                }
                DeadLock.a1.release(); // 释放
                DeadLock.a2.release();
                // 这里只是为了演示，所以tryAcquire只用1秒，而且B要给A让出能执行的时间，否则两个永远是死锁
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}