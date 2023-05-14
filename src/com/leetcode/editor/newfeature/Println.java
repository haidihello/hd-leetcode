package com.leetcode.editor.newfeature;

public class Println {
  static   class ThreadTest implements Runnable {
      private  int i = 1;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    notifyAll();
                    if (i < 100) {
                        System.out.println(Thread.currentThread().getName() + i++);
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        break;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();

    }
    public void test(){}
}
