package com.leetcode.editor.juc;



import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: HaiDi
 * @Date: 2022/12/8 18:36
 */
public class ThreadPool {
    public void threadPoolTest() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);               // 初始化线程数
        executor.setMaxPoolSize(20);                // 最大线程数
        executor.setQueueCapacity(200);             // 缓冲队列
        executor.setKeepAliveSeconds(60);           // 允许空闲时间/秒
        executor.setThreadNamePrefix("taskExecutor-");// 线程池名前缀-方便日志查找
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();   // 初始化
    }
}
