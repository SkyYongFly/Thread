package com.skylaker.concurrent.lock;

import com.sun.org.apache.regexp.internal.RE;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/16 10:14 PM
 */
public class ReadWriteLockService {
    private static int val = 0;

    // 读写锁对象
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 读锁
    private static Lock readLock = readWriteLock.readLock();
    // 写锁
    private static Lock writeLock = readWriteLock.writeLock();


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
                readLock.lock();
                Thread.sleep(2000);
                System.out.println(System.currentTimeMillis() + " val值：" + val);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
    }

    static class WriteTask implements Runnable {
        public void run() {
            try {
                writeLock.lock();
                Thread.sleep(1000);
                System.out.println(System.currentTimeMillis() + " 正在写数据");
                val++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
    }
}
