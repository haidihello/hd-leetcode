package com.leetcode.editor.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 循环屏障 多线程会阻塞到计数器为0的时候继续执行
 * parties 设置要小于线程数
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 循环屏障
        CyclicBarrier cyclicBarrier = new CyclicBarrier(11, () -> System.out.println("计数器为0了"));
        // 创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            service.submit(() -> {
                Thread currThread = Thread.currentThread();
                System.out.println("执行线程：" + currThread);
                try {
                    Thread.sleep(500 * finalI);
                    cyclicBarrier.await();  // 执行阻塞等待（计数器-1，直到循环计数器为0的时候再执行后面代码）
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    System.out.println("异常捕获" + e);
                }
                System.out.println("线程执行完成!" + currThread.getName());
            });
        }
    }
}