package com.leetcode.editor.juc;

/**
 * @Author: HaiDi
 * @Date: 2022/10/26 16:54
 */
public class ThreadTest {
        public static volatile long[] arr = new long[32];//5565 1719 9119 9566 5027 9075
//    public static volatile long[] arr = new long[2];//7815 1188 9814
//    public static volatile long[] arr = new long[8];//7366 1220 8742

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < Integer.MAX_VALUE; i++) {
                arr[0] = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (long i = 0; i < Integer.MAX_VALUE; i++) {
                arr[1] = i;
            }
        });
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }
}
