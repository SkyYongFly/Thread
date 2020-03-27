package com.skylaker.thread.suspend;

/**
 * 线程挂起suspend和继续执行resume
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/24 11:27 PM
 */
public class ThreadSuspendResume {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread1 = new MyThread("线程1");
        MyThread myThread2 = new MyThread("线程2");

        myThread1.start();
        myThread2.start();

        // 线程1继续执行
        System.out.println("通知线程1继续执行");
        myThread1.resume();
        // 线程2继续执行
        System.out.println("通知线程2继续执行");
        myThread2.resume();

        myThread1.join();
        myThread2.join();
    }

    static class MyThread extends Thread {
        private String threadName;

        MyThread(String threadName){
            this.threadName = threadName;
        }

        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程：" + threadName + " 开始运行");
                // 线程挂起
                Thread.currentThread().suspend();
                System.out.println("线程：" + threadName + " 结束运行");

            }
        }
    }
}
