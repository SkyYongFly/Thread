package com.skylaker.pool.factory;

import com.skylaker.pool.reject.ThreadPoolRejectedHandler;

import java.util.concurrent.*;

/**
 * 线程池中线程创建工厂
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/2 10:40 PM
 */
public class ThreadPoolFactory {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        System.out.println("主线程组：" + Thread.currentThread().getThreadGroup().getName());

        ExecutorService es = getThreadPool1();
        for(int i = 0; i < 10; i++){
            es.submit(task);
        }
    }

    // 自定义线程创建工厂
    static ExecutorService getThreadPool1(){
        return new ThreadPoolExecutor(
                // 核心大小：1 ；
                1,
                // 最大大小 2；
                2,
                // 空闲时间：0s
                0, TimeUnit.SECONDS,
                // 有界队列
                new ArrayBlockingQueue<Runnable>(2),
                // 自定义线程创建工厂
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        System.out.println("创建线程对象：" + thread);
                        return thread;
                    }
                },
                // 静默拒绝策略
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    // 不指定，即采用默认的线程创建工厂
    static ExecutorService getThreadPool2(){
        return new ThreadPoolExecutor(
                // 核心大小：1 ；
                1,
                // 最大大小 2；
                2,
                // 空闲时间：0s
                0, TimeUnit.SECONDS,
                // 有界队列
                new ArrayBlockingQueue<Runnable>(2),
                // 静默拒绝策略
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    static class MyTask implements Runnable {
        public void run() {
            System.out.println("当前执行任务线程ID：" + Thread.currentThread().getId());
            System.out.println("当前执行任务线程组：" + Thread.currentThread().getThreadGroup().getName());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
