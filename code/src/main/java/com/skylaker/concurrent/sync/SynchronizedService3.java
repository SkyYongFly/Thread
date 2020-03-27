package com.skylaker.concurrent.sync;

import org.junit.jupiter.api.Test;

/**
 * synchronized 修饰静态方法
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/7 10:21 PM
 */
public class SynchronizedService3 {
    private static int sum = 0;

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

    static class MyTask implements Runnable {
        private int num = 0;

        MyTask(int num){
            this.num = num;
        }

        public void run() {
            for(int i = 0; i < num; i++){
                add3();
                Thread.yield();
            }
        }

        // 修饰静态方法
        private synchronized static void add3(){
            sum++;
        }
    }
}
