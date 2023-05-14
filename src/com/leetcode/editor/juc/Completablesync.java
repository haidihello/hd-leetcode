package com.leetcode.editor.juc;


import java.util.concurrent.CompletableFuture;

public class Completablesync {
    //异步线程
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        });

    }
}
