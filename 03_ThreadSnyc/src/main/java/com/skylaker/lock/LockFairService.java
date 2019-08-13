package com.skylaker.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 Lock : 公平锁
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/13 8:09 PM
 */
public class LockFairService {
    // 设置公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        Thread thread1 = new Thread(task, "线程1");
        Thread thread2 = new Thread(task, "线程2");
        thread1.start();
        thread2.start();
    }

    static class MyTask implements Runnable {
        public void run() {
            while (true) {
                try{
                    lock.lock();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "获取到锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
