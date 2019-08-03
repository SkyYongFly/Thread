package com.skylaker.pool.worker;

import java.util.concurrent.*;

/**
 * 线程池执行线程状态监控
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/3 9:02 AM
 */
public class ThreadExecute {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(
                2,
                3,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        ){
            // 线程执行之前调用
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行任务：" + ((MyTask)r).getId());
            }

            // 线程执行之后调用
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("已执行完任务：" + ((MyTask)r).getId());
            }

            // 线程池关闭时调用
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for(int i = 0; i < 10; i++){
            MyTask task = new MyTask(i);
//            es.submit(task);
            es.execute(task);
        }

        es.shutdown();
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
