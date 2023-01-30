package com.leetcode.editor.juc;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量可以保证每个线程都要获取令牌并释放后才能被下一线程操作
 */
public class SemaphoreDemo {
    // 创建信号量
    static Semaphore semaphore = new Semaphore(1);
    private volatile int count = 1;
 
    public static void main(String[] args) {
        // 创建固定线程数的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
 
        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(100 * i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            service.submit(() -> {
                Thread currThread = Thread.currentThread();
                System.out.println("进入线程：" + currThread.getName());
                try {
                    // 获取令牌
                    semaphore.acquire();
                    System.out.println(currThread.getName() + "得到令牌：" + LocalDateTime.now());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(currThread.getName() + "释放令牌：" + LocalDateTime.now());
                    semaphore.release();
                }
            });
        }
        service.shutdown();
    }
}