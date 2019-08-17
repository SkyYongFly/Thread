package com.skylaker.park;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程阻塞工具 LockSupportPark
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/17 7:01 PM
 */
public class ParkService {
    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        Thread thread1 = new Thread(myTask);
        Thread thread2 = new Thread(myTask);
        thread1.start();
        thread2.start();

        Thread.sleep(2000);

        // 结束线程阻塞等待
//        LockSupport.unpark(thread1);
//        LockSupport.unpark(thread2);

        // 通过中断方式结束阻塞
        thread1.interrupt();
        LockSupport.unpark(thread2);
    }

    static class MyTask implements Runnable {
        public void run() {
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getId() + " 线程执行");

            // 线程阻塞等待
            LockSupport.park();
            // park被中断不主动抛出异常，但是可以通过线程状态监测
            if(Thread.interrupted()){
                System.out.println(Thread.currentThread().getId() + " 线程被中断");
            }

            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getId() + " 执行结束");
        }
    }
}
