package com.leetcode.editor.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 计数器 等所有线程都执行完再输出结果
 * 如果计数器和线程数不相等会持续等待
 */
public class CountDownLatchDemo {
    public static void main(String[] args)  {
        // 创建计算器
        CountDownLatch countDownLatch = new CountDownLatch(5);
        // 创建线程池
        ExecutorService service = Executors.newFixedThreadPool(5);
        // 创建新线程执行任务
        for (int i = 1; i <= 5; i++) {
            service.submit(() -> {
                Thread currThread = Thread.currentThread();
                Long stat = System.currentTimeMillis();
                System.out.println(currThread.getName() + "开始起跑");
//                int runTime = new Random().nextInt(5) + 1;
                int runTime = 1;
                try {
                    TimeUnit.SECONDS.sleep(runTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(currThread.getName() + "到达终点，用时：" + runTime + "耗时" + (System.currentTimeMillis() - stat));
                countDownLatch.countDown();
                System.out.println(countDownLatch);
            });
        }

        try {
            countDownLatch.await();
            countDownLatch.await(4,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("比赛结果宣布！");
        service.shutdown();
    }
}