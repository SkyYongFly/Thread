package com.skylaker.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 Lock : 锁申请等待限时
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/13 8:09 PM
 */
public class LockWaitTimeService {
    // 锁1
    private static ReentrantLock lock1 = new ReentrantLock();
    // 锁2
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyTask(1));
        Thread thread2 = new Thread(new MyTask(2));
        thread1.setName("线程1");
        thread2.setName("线程2");
        thread1.start();
        thread2.start();

//        Thread.sleep(1000);
//        thread1.interrupt();
    }

    static class MyTask implements Runnable {
        private int i ;

        MyTask(int i){
            this.i = i;
        }

        public void run() {
            try{
                if(1 == i){
                    // 先获取锁1
                    System.out.println(Thread.currentThread().getName() + "准备获取锁1");
                    // 在一定时间内尝试获取锁，如果获取不到则放弃
                    lock1.tryLock(5, TimeUnit.SECONDS);
                    if(lock1.isLocked()){
                        System.out.println("lock1被获取~~");
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 再获取锁2
                    System.out.println(Thread.currentThread().getName() + "准备获取锁2");
                    lock2.tryLock(5, TimeUnit.SECONDS);
                    if(lock2.isLocked()){
                        System.out.println("lock2被获取~~");
                    }
                } else {
                    // 先获取锁2
                    System.out.println(Thread.currentThread().getName() + "准备获取锁2");
                    lock2.tryLock(5, TimeUnit.SECONDS);
                    if(lock2.isLocked()){
                        System.out.println("lock2被获取~~");
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 再获取锁1
                    System.out.println(Thread.currentThread().getName() + "准备获取锁1");
                    lock1.tryLock(5, TimeUnit.SECONDS);
                    if(lock1.isLocked()){
                        System.out.println("lock1被获取~~");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }

                System.out.println("线程执行结束：" + Thread.currentThread().getName());
            }
        }
    }
}
