package com.skylaker.thread.interrupt;

/**
 * 线程中断
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/22 11:02 PM
 */
public class ThreadInterrupted {

    public static void main(String[] args) throws InterruptedException {
//        testInterrupt();
//        testReolveInterrupt();
        testSleepInterrupt();
    }


    /**
     * 如果子线程不对中断通知进行处理，那么将不会退出处理逻辑
     * @throws InterruptedException
     */
    public static void testInterrupt() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    System.out.println("子线程正在运行：" + System.currentTimeMillis());
                    Thread.yield();
                }
            }
        };

        thread.start();
        Thread.sleep(2000);
        // 通知子线程中断
        thread.interrupt();
    }

    /**
     * 子线程对中断通知进行响应
     * @throws InterruptedException
     */
    public static void testReolveInterrupt() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("子线程收到中断通知，准备退出！");
                        break;
                    }

                    System.out.println("子线程正在运行：" + System.currentTimeMillis());
                    Thread.yield();
                }
            }
        };

        thread.start();
        Thread.sleep(200);
        // 通知子线程中断
        thread.interrupt();
    }

    /**
     * 休眠过程中中断
     * @throws InterruptedException
     */
    public static void testSleepInterrupt() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run(){
                while (true) {
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("子线程收到中断通知，准备退出！");
                        break;
                    }

                    System.out.println("子线程正在运行：" + System.currentTimeMillis());

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("子线程休眠中断异常");
                        // 异常中断标志位被清除，需要重新设置，让子线程处理退出，避免直接退出导致后续代码无法执行
                        Thread.currentThread().interrupt();
                    }

                    System.out.println("休眠后逻辑内容：" + System.currentTimeMillis());
                    Thread.yield();
                }
            }
        };

        thread.start();
        Thread.sleep(300);
        // 通知子线程中断
        thread.interrupt();
    }
}
