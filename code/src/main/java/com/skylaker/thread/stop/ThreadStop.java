package com.skylaker.thread.stop;

import org.junit.jupiter.api.Test;

/**
 * 终止线程
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/20 8:54 PM
 */
public class ThreadStop {
    @Test
    public void testStopThread(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("Hello, Thread !");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        thread.start();
        // 终止线程
        thread.stop();
    }
}
