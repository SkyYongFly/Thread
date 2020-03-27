package com.skylaker.thread.join;

/**
 * 等待线程结束运行 join
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/25 12:42 AM
 */
public class ThreadJoin {
    private static volatile int sum = 0;

    public static void main(String[] args){
        MyThread myThread = new MyThread();
        myThread.start();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("计算和为：" + sum);

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++){
                sum += i;
            }
        }
    }
}
