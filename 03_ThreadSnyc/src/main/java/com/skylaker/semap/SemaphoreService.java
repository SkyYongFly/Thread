package com.skylaker.semap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量 Semaphore
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/13 8:09 PM
 */
public class SemaphoreService {
    // 定义信号量：这里定义了一个包含5个凭证的信号量
    static Semaphore semaphore = new Semaphore(5);

    // 定义信号量：这里定义了一个包含5个凭证的信号量，且设置线程公平获取
//    static Semaphore semaphore = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for(int i = 0; i < 100; i++){
            executor.submit(new MyTask());
        }
    }

    static class MyTask implements Runnable {
        public void run() {
            boolean hasAcquire = false;
            try {
                // 线程先申请信号量凭证
//                semaphore.acquire();

                // 尝试获取凭证
                if(semaphore.tryAcquire()){
                    hasAcquire = true;
                    System.out.println(Thread.currentThread().getId() + " : "
                            + System.currentTimeMillis() +  " : 正在处理逻辑");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 线程需要释放信号量凭证
                if(hasAcquire){
                    semaphore.release();
                }
            }

        }
    }
}
