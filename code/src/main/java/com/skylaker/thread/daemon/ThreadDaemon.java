package com.skylaker.thread.daemon;

/**
 * 守护线程
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/26 10:14 PM
 */
public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        // 设置线程为守护线程
        myThread.setDaemon(true);

        // 主线程开始
        System.out.println("主线程正在运行");
        myThread.start();

        Thread.sleep(5000);
        // 主线程结束
        System.out.println("主线程结束运行");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("我是守护线程，正在运行~~~");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
