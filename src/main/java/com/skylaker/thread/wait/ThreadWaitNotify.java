package com.skylaker.thread.wait;

/**
 * 多线程协作：wait notify
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/23 10:31 PM
 */
public class ThreadWaitNotify {
    final static Object object = new Object();

    public static void main(String[] args){
        WaitThread waitThread = new WaitThread();
        NotifyThread notifyThread = new NotifyThread();
        waitThread.start();
        notifyThread.start();
    }

    // 阻塞等待 wait
    static class WaitThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + " 等待线程处理逻辑 1 ");

                try {
                    // 当前锁对象锁住的线程进入阻塞等待，并释放锁
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + " 等待线程继续处理逻辑 2");
            }
        }
    }

    // 通知唤醒 notify
    static class NotifyThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + " 通知线程处理逻辑 1");

                // 当前锁对象锁住的线程通知当前锁对象等待队列中的某个线程唤醒
                object.notify();

                System.out.println(System.currentTimeMillis() + " 通知线程处理逻辑 2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
