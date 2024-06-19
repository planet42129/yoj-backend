package com.yhh.yoj.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hyh
 * @date 2024/6/19
 */
    @RestController
    @RequestMapping("/queue")
    @Slf4j
    @Profile({"dev", "test", "local"})
    public class ThreadQueueController {

        @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 接收name参数，模拟任务的执行
     *
     * @param name
     */
    @GetMapping("/add")
    public void add(String name) {
        //运行异步任务
        CompletableFuture.runAsync(() -> {
            log.info("任务执行中: " + name + ",执行线程为：" + Thread.currentThread().getName());
            try {
                //模拟任务的执行
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPoolExecutor); //异步任务在threadPoolExecutor中执行
    }


    /**
     * 返回线程池的状态信息
     *
     * @return
     */
    @GetMapping("/get")
    public String get() {
        //创建一个HashMap存储线程池的状态信息
        Map<String, Object> map = new HashMap<>();

        int size = threadPoolExecutor.getQueue().size();
        map.put("队列长度", size);

        long taskCount = threadPoolExecutor.getTaskCount();
        map.put("任务总数", taskCount);

        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        map.put("已经完成的任务数", completedTaskCount);

        int activeCount = threadPoolExecutor.getActiveCount();
        map.put("正在执行任务的线程数", activeCount);

        return JSONUtil.toJsonStr(map);
    }
}
