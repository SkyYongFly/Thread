package com.skylaker.concurrent.barrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 循环栅栏 CyclicBarrier ：异常情况
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/17 12:36 PM
 */
public class CyclicBarrier2Service {
    // 控制线程，让目标数量线程等待一起执行
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
        public void run() {
            System.out.println("线程已经全部到位~~~");
        }
    });


    public static void main(String[] args) {
        MyTask myTask = new MyTask();

        for(int i = 0; i < 16; i++){
            new Thread(myTask).start();
        }
    }

    static class MyTask implements Runnable {
        public void run() {
            try {
                // 每个线程一开始都等待，等待指定数量的线程就位
                cyclicBarrier.await(5, TimeUnit.SECONDS);
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getId() + " 线程开始运行");
                Thread.sleep(Math.abs(new Random().nextInt()%10000));

                // 线程再次等待，等待所有线程执行完毕
                cyclicBarrier.await(5, TimeUnit.SECONDS);
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getId() + " 线程执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
