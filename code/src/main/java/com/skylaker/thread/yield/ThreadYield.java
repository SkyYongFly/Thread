package com.skylaker.thread.yield;

/**
 * 线程谦让 yield
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/25 12:56 AM
 */
public class ThreadYield {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.setPriority(Thread.MIN_PRIORITY);
        myThread2.setPriority(Thread.MAX_PRIORITY);

        myThread1.start();
        myThread2.start();
    }

    static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("线程1：开始运行");
            Thread.currentThread().yield();
            System.out.println("线程1：结束运行");
        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {
            System.out.println("线程2：运行");
        }
    }
}
