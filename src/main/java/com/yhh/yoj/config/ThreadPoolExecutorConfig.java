package com.yhh.yoj.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hyh
 * @date 2024/6/19
 */
@Configuration
public class ThreadPoolExecutorConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {

        //八股：局部内部类的应用
        //自定义线程工厂
        ThreadFactory threadFactory = new ThreadFactory(){
            //初始化线程数为1
            private int threadCount = 1;

            /**
             * 每当线程池需要创建新线程时，就会调用newThread方法
             * @NotNull Runnable r 表示提交的参数r（Runnable任务）永远不为空
             * @param r a runnable to be executed by new thread instance
             * @return
             */
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("OJ-Thread-" + threadCount);
                threadCount++;
                return thread;
            }
        };

        //创建线程池，自定义参数
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                threadFactory//使用自定义的线程工厂
        );
        //返回创建的线程池
        return threadPoolExecutor;
    }



}
