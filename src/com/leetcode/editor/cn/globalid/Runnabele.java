package com.leetcode.editor.cn.globalid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: HaiDi
 * @Date: 2021/12/27 19:41
 */
public class Runnabele {
    public static void main(String[] args) {
//        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(1000);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                20,
                50,
                300,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(500));
        for (int i = 0; i < 20; i++) {
            final int index = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + Thread.currentThread().getName());
                    Long start = System.currentTimeMillis();
                    List orderNos = Collections.synchronizedList(new ArrayList());
                    IntStream.range(0, 3000).parallel().forEach(i -> {
                        orderNos.add(OrderGen2Test.generateOrderNo());
                    });

                    List filterOrderNos = (List) orderNos.stream().distinct().collect(Collectors.toList());
                    System.out.println("订单样例：" + orderNos.get(2));
                    System.out.println("生成订单数：" + orderNos.size());
                    System.out.println("过滤重复后订单数：" + filterOrderNos.size());
                    System.out.println("重复订单数：" + (orderNos.size() - filterOrderNos.size()));
                    System.out.println("总耗时" + (System.currentTimeMillis() - start));

                }
            });
        }
        System.out.println("================");
        threadPoolExecutor.shutdown();
    }
}
