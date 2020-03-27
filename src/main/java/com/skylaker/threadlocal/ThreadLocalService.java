package com.skylaker.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程本地变量 ThreadLocal
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/22 10:27 PM
 */
public class ThreadLocalService {
    private static ThreadLocal<SimpleDateFormat> threadLocal =
            new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        for(;;){
            pool.execute(new MyTask());
        }
    }


    static class MyTask implements Runnable {
        @Override
        public void run() {
            if(null == threadLocal.get()){
                threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }

            String date = threadLocal.get().format(new Date(Math.abs(new Random().nextLong())));
            System.out.println(date);
        }
    }

}
