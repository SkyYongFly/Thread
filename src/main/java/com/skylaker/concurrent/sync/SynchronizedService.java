package com.skylaker.concurrent.sync;

import org.junit.jupiter.api.Test;

/**
 * synchronized 修饰实例方法
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/7 10:21 PM
 */
public class SynchronizedService {
    private int sum = 0;

    @Test
    public void test() throws InterruptedException {
        MyTask myTask = new MyTask(10000);

        Thread thread1 = new Thread(myTask);
        Thread thread2 = new Thread(myTask);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("计算结果：" + sum);
    }

    class MyTask implements Runnable {
        private int num = 0;

        MyTask(int num){
            this.num = num;
        }

        public void run() {
            for(int i = 0; i < num; i++){
                add();
                Thread.yield();
            }
        }

        // 修饰实例方法
        private synchronized void add(){
            sum++;
        }
    }
}
