package com.skylaker.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 单个大小、定时性的线程池
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/28 4:11 PM
 */
public class SingleScheduledThreadPool {
    public static void main(String[] args){
        // 创建一个大小为1、可周期性或某个延时过后执行的的线程池
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // 定义线程任务
        Thread task = new Thread(new MyThread());
        // 提交任务到线程池
        System.out.println("提交任务时间：" + System.currentTimeMillis());

        // 指定延时后执行
//        executorService.schedule(task, 5, TimeUnit.SECONDS);
        // 固定频率周期执行
//        executorService.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
        // 指定延时周期执行
        executorService.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS);
    }

    static class MyThread implements Runnable {
        public void run() {
            System.out.println("线程ID：" + Thread.currentThread().getId()
                    + " ; 当前时间：" + System.currentTimeMillis());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
