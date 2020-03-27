package com.skylaker.threadpool.reject;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * 线程池拒绝策略
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/2 9:12 PM
 */
public class ThreadPoolRejectedHandler {

    public static void main(String[] args) {
        System.out.println("主线程ID：" + Thread.currentThread().getId());

        ExecutorService es = getMyThreadPool3();
        for(int i = 0; i < 20; i++){
            MyTask myTask = new MyTask(i);
            es.execute(myTask);
//            es.submit(myTask);
        }
    }

    /**
     * 核心线程数量 2；
     * 最大线程数量：5；
     * 线程空闲等待时间：0s;
     * 等待队列：大小为5的链表队列；
     * 拒绝策略：直接拒绝
     */
    static ExecutorService getMyThreadPool1() {
        return new ThreadPoolExecutor(
                2,
                5,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(5),
                new ThreadPoolExecutor.AbortPolicy());

    }

    /**
     * 核心线程数量 2；
     * 最大线程数量：5；
     * 线程空闲等待时间：0s;
     * 等待队列：大小为5的链表队列；
     * 拒绝策略：调用者运行丢弃的任务
     */
    static ExecutorService getMyThreadPool2() {
        return new ThreadPoolExecutor(
                2,
                5,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(5),
                new ThreadPoolExecutor.CallerRunsPolicy());

    }

    /**
     * 核心线程数量 2；
     * 最大线程数量：5；
     * 线程空闲等待时间：0s;
     * 等待队列：大小为5的链表队列；
     * 拒绝策略：丢弃队列中最旧的任务以执行当前新提交的任务
     */
    static ExecutorService getMyThreadPool3() {
        return new ThreadPoolExecutor(
                2,
                5,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(5),
                new ThreadPoolExecutor.DiscardOldestPolicy(){
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                        BlockingQueue queue = e.getQueue();
                        StringBuffer buffer = new StringBuffer(10);
                        for(Object task : queue){
                            buffer.append(((MyTask)task).getId()).append(",");
                        }

                        System.out.println("将要执行的任务ID：" + ((MyTask)r).getId() + "；当前队列：" + buffer.toString() +
                                "；将被丢弃的任务ID：" + ((MyTask)e.getQueue().peek()).getId());
                        super.rejectedExecution(r, e);
                    }
                }
        );
    }

    /**
     * 核心线程数量 2；
     * 最大线程数量：5；
     * 线程空闲等待时间：0s;
     * 等待队列：大小为5的链表队列；
     * 拒绝策略：丢弃队列中最旧的任务以执行当前新提交的任务
     */
    static ExecutorService getMyThreadPool4() {
        return new ThreadPoolExecutor(
                2,
                5,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(5),
                new ThreadPoolExecutor.DiscardPolicy()
        );

    }

    /**
     * 核心线程数量 2；
     * 最大线程数量：5；
     * 线程空闲等待时间：0s;
     * 等待队列：大小为5的链表队列；
     * 拒绝策略：自定义拒绝策略，打印拒绝的任务信息
     */
    static ExecutorService getMyThreadPool5() {
        return new ThreadPoolExecutor(
                2,
                5,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(5),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("被拒绝的任务：" + r.toString());
                    }
                }
        );
    }

    static class MyTask implements Runnable {
        private int id;

        MyTask(int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }

        public void run() {
            System.out.println("当前执行任务线程ID：" + Thread.currentThread().getId() + "  执行任务ID：" + id);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
