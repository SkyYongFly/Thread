package com.skylaker.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器 CountDownLatch
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/17 11:23 AM
 */
public class LatchService {
    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        MyTask myTask = new MyTask();

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0 ; i < 5; i++){
            pool.submit(myTask);
        }

        try {
            // 等待计时器计数结束
            countDownLatch.await();
            // 等待的线程开始执行（这里就是主线程）
            System.out.println("\n等待线程开始执行~~~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }

    static class MyTask implements Runnable {
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getId() + " 任务执行完毕");

                // 减少一个计数器值
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
