package com.skylaker.thread.priority;

/**
 * 线程优先级
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/26 10:33 PM
 */
public class ThreadPriority2 {

    public static void main(String[] args) throws InterruptedException {
        HighThread highThread = new HighThread();
        LowerThread lowerThread = new LowerThread();

        highThread.setPriority(Thread.MAX_PRIORITY);
        lowerThread.setPriority(Thread.MIN_PRIORITY);

        highThread.start();
        lowerThread.start();
    }

    static class HighThread extends Thread {
        private int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriority2.class){
                    count++;

                    if(count > 100000000){
                        System.out.println(System.currentTimeMillis() + ": 高优先级线程执行完毕！");
                        break;
                    }
                }
            }
        }
    }

    static class LowerThread extends Thread {
        private int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriority2.class) {
                    count++;

                    if (count > 100000000) {
                        System.out.println(System.currentTimeMillis() + ": 低优先级线程执行完毕！");
                        break;
                    }
                }
            }
        }
    }
}
