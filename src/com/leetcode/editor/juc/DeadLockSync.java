package com.leetcode.editor.juc;

/**
 * @Author: HaiDi
 * @Date: 2023/4/14 13:23
 */
 class DeadLockSync {
    static Object one = new Object();
   static Object two = new Object();

    static class ThreadOne implements Runnable{
        @Override
        public void run(){
            synchronized(one){
                System.out.println(Thread.currentThread().getName() + " acquired first lock");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (two){
                    System.out.println("two ok");
                }
            }

        }
    }
  static   class ThreadTwo implements Runnable{
        @Override
        public void run(){
            synchronized(two){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (one){
                    System.out.println(Thread.currentThread().getName() + " acquired second lock");
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadOne threadone = new ThreadOne();
        Thread t1 = new Thread(threadone);
        t1.start();
        ThreadTwo threadTwo = new ThreadTwo();
        Thread t2 = new Thread(threadTwo);
        t2.start();

    }
}
