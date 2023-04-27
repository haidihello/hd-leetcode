package com.leetcode.editor.juc;


import org.junit.Test;

import java.util.concurrent.CompletableFuture;

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
    @Test
    public void print(){

        for ( int i = 0; i <100 ; i++) {
            if (i % 2 == 0) {
                int finalI = i;
               CompletableFuture completableFuture =  CompletableFuture.runAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + finalI);
                });
                completableFuture.join();
            } else {
                System.out.println(Thread.currentThread().getName() + i);
            }

        }

    }
}
