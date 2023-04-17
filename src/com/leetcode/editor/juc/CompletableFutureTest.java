package com.leetcode.editor.juc;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * 异步并获取返回值
 * @Author: HaiDi
 * @Date: 2022/10/9 15:29
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        List<String> checkList = new ArrayList();
        checkList.add("1");
        checkList.add("2");
        checkList.add("3");
        checkList.add("4");
        checkList.add("5");
        StringBuffer sb = new StringBuffer();
        CountDownLatch countDownLatch = new CountDownLatch(checkList.size());
        for (String check : checkList) {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                return queryHistoryAndUpdateCheck(check);
            }).whenComplete((res, throwable) -> {
                countDownLatch.countDown();
                if (StringUtils.isNotBlank(res)) {
                    sb.append(res);
                }
            });
        }
        countDownLatch.await();
        System.out.println("全部执行结束");
        System.out.println("sb:" + sb);
        CompletableFuture.runAsync(() -> {
        });


    }

    static String queryHistoryAndUpdateCheck(String check) {
        if ("2".equals(check)) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(check+Thread.currentThread().getName());
        return check;
    }
}
