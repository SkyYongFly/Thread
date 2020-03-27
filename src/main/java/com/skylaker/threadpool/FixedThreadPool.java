package com.skylaker.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定大小的线程池
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/28 4:11 PM
 */
public class FixedThreadPool {
    public static void main(String[] args){
        // 创建一个大小为5的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 定义线程任务
        Thread task = new Thread(new MyThread());
        // 提交任务到线程池
        for(int i = 0; i < 10; i++){
            executorService.submit(task);
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
