package com.leetcode.editor.juc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: HaiDi
 * @Date: 2023/4/17 17:45
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 5, 30,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            CompletableFuture future = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());

            }, executor);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void supersync() {
        List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            CompletableFuture future = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                return "result";
            }).whenComplete((r, t) ->
                    list.add(r)
            );

        }
        list.forEach(System.out::println);
    }
}
