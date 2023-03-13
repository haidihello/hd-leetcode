package com.leetcode.editor.juc;



/**
 * 使用两个线程分别打印1-100
 *
 * @Author: HaiDi
 * @Date: 2022/12/8 18:36
 */
public class ThreadPrint {

 static class ThreadTest implements Runnable {
     private int number = 1;
     @Override
     public void run() {
         while (true) {
             synchronized (this) {
                 notifyAll();
                 if (number <= 100) {
                     System.out.println(Thread.currentThread().getName() + " " + number++);
                     try {
                         wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 } else {
                     break;
                 }
             }
         }
     }
 }
    public static void main(String[] args) {
        ThreadTest  test1 = new ThreadTest();
        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test1);
        t1.start();
        t2.start();

    }
}
