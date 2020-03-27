package com.skylaker.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 动态大小的线程池
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/28 4:11 PM
 */
public class CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个大小可动态调整的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义线程任务
        Thread task = new Thread(new MyThread());
        // 提交任务到线程池
        for(int i = 0; i < 10; i++){
            executorService.submit(task);
            // 每隔两秒提交一个任务
//            Thread.sleep(2000);
        }
        // 关闭线程池
        executorService.shutdown();
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
