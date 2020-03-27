package com.skylaker.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 Lock 等待通知唤醒：Condition
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/13 8:09 PM
 */
public class ConditionService {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyTask());
        thread.start();
        Thread.sleep(2000);

        // 通知子线程唤醒
        lock.lock();
        // 在同一个condition上进行通知
        condition.signal();
        lock.unlock();
    }

    static class MyTask implements Runnable {
        public void run() {
            try{
                lock.lock();
                System.out.println(System.currentTimeMillis() + " : 处理一些逻辑~~~");
                // 在当前锁对象的Condition上进行等待
                condition.await();
                System.out.println(System.currentTimeMillis() + " : 处理后续逻辑………");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
