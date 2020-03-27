package com.skylaker.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/16 10:14 PM
 */
public class Lock2Service {
    private static int val = 0;
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReadTask readTask = new ReadTask();
        WriteTask writeTask = new WriteTask();

        for(int i = 0; i < 2; i++){
            new Thread(writeTask).start();
        }

        for(int i = 0; i < 20; i++){
            new Thread(readTask).start();
        }
    }

    static class ReadTask implements Runnable {
        public void run() {
            try {
                lock.lock();
                Thread.sleep(2000);
                System.out.println(System.currentTimeMillis() + " val值：" + val);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class WriteTask implements Runnable {
        public void run() {
            try {
                lock.lock();
                Thread.sleep(1000);
                System.out.println(System.currentTimeMillis() + " 正在写数据");
                val++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
