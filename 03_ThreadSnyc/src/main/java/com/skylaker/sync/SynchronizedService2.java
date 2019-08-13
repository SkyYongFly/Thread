package com.skylaker.sync;

import org.junit.jupiter.api.Test;

/**
 * synchronized 修饰代码块
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/7 10:21 PM
 */
public class SynchronizedService2 {
    private  int sum = 0;
    private Object lock = new Object();

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
                add3();
                Thread.yield();
            }
        }

        // 修饰代码块：指定实例对象锁
        private void add2(){
            synchronized (lock){
                sum++;
            }
        }

        // 修饰代码块：指定类字节码对象锁
        private void add3(){
            synchronized (MyTask.class){
                sum++;
            }
        }
    }
}
