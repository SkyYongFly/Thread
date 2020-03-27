package com.skylaker.thread.create;

import org.junit.jupiter.api.Test;

/**
 * 新建线程
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/20 10:45 AM
 */
public class ThreadNew {

    public static void main(String[] args){
        // 方式一: 通过继承Thread
        Thread01 thread01 = new Thread01();
        thread01.start();

        // 方式二：通过实现Runnable接口
//        Thread02 thread02 = new Thread02();
//        thread02.start(); 没有该方法，更不可能执行，因为这是Thread02实例，不是线程Thread实例
        Thread thread02 = new Thread(new Thread02());
        thread02.start();

        /// 打印当前主线程ID
        System.out.println("主线程ID：" + Thread.currentThread().getId());
    }

    /**
     * 直接执行Run方法其实就是执行普通的Java方法，并非通知JVM去创建一个线程执行，
     * 所以这种方式执行的对象还是当前主线程
     */
    @Test
    public void testRunStart(){
        Thread t1 = new Thread01();
        t1.run();

        Thread t2 = new Thread(new Thread02());
        t2.run();

        System.out.println("主线程ID：" + Thread.currentThread().getId());
    }

    /**
     * 线程对象理解：
     * 我们定义的线程其实真实的理解应该是我们定义了线程需要执行的任务内容，即run方法内的程序指令，
     * 只不过需要执行这段指令的方式是让JVM去创建一个线程去执行，而非当前主线程；
     * new Thread 然后 start 就是通知JVM去创建线程执行指令，只不过不同线程执行的指令可以是同一个
     */
    @Test
    public void testThread(){
        Thread02 thread02 = new Thread02();

        Thread t1 = new Thread(thread02);
        Thread t2 = new Thread(thread02);
        Thread t3 = new Thread(thread02);
        t1.start();
        t2.start();
        t3.start();
    }

    @Test
    public void testStart(){
        Thread t1 = new Thread(new Thread02());
        t1.start();
        t1.start();
    }

    /**
     * 方式一: 通过继承Thread
     */
    static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("Hello, I'm a Thread by extends !");
            System.out.println("子线程ID：" + Thread.currentThread().getId());
        }
    }

    /**
     * 方式二：通过实现Runnable接口
     */
    static class Thread02 implements Runnable {
        public void run() {
            System.out.println("Hi, I'm a Thread by implements !");
            System.out.println("子线程ID：" + Thread.currentThread().getId());
        }
    }
}
