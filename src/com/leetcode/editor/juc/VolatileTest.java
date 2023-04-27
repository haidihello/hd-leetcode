package com.leetcode.editor.juc;

public class VolatileTest {

//    private static boolean flag = true; //不用volatile修饰
    private volatile static boolean flag = true; //用volatile修饰

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());

        t1.start();
        Thread.sleep(1000); //暂停1秒 保证线程1 启动并运行。
        t2.start();
    }

    /**
     * 线程1 一个循环，如果 flag为false 跳出循环
     */
    static class Thread1 implements Runnable {
        @Override
        public void run() {
            while (flag) {

            }
            System.out.println("thread1-run");
        }
    }

    /**
     * 线程2  2秒后将flag改成false
     */
    static class Thread2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2-run");
            flag = false;
            System.out.println("flag 被改成false");
        }
    }
}