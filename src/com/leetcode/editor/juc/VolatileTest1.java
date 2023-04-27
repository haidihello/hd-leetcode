package com.leetcode.editor.juc;

/**
 * @Author: HaiDi
 * @Date: 2023/4/24 15:51
 */
public class VolatileTest1 {
    private static boolean flags = true;
//    static volatile boolean flags = true;
//
    public static void main(String[] args) throws Exception {
        new Thread(new Volatile()).start();
        Thread.sleep(1000);
        new Thread(new Volatile2()).start();
    }

    static class Volatile extends Thread {

        @Override
        public void run() {
            while (flags) {
                System.out.println("flags");
            }
            System.out.println("Volatile end run......");
        }
    }

    static class Volatile2 extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            flags = false;
            System.out.println("Volatile2 end run......");
        }
    }
}
