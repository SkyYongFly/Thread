package com.skylaker.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 Lock 多次获取锁
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/13 8:09 PM
 */
public class LockTimesService {
    // 定义锁
    private static ReentrantLock lock = new ReentrantLock();
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("计算结果：" + i);
    }

    static class MyTask implements Runnable {
        public void run() {
            for(int j = 0; j < 10000; j++){
                // 加锁
                lock.lock();
                lock.lock();
                try{
                    i++;
                } finally {
                    // 释放锁
                    lock.unlock();
                    lock.unlock();
                }

                // 线程谦让，为了测试CPU抢占调度导致共享变量计算冲突
                Thread.yield();
            }
        }
    }
}
