package com.skylaker.thread.group;

/**
 * 线程组
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/26 9:57 PM
 */
public class ThreadGroupCls {

    public static void main(String[] args) throws InterruptedException {
        // 定义线程组
        ThreadGroup threadGroup = new ThreadGroup("用户线程组");
        // 创建线程，传入线程组、定义线程信息
        Thread t1 = new Thread(threadGroup, new MyThread(), "获取用户信息线程");
        Thread t2 = new Thread(threadGroup, new MyThread(), "设置用户名称线程");

        t1.start();
        t2.start();

        Thread.sleep(1000);
        System.out.println("线程组活动线程数量："+ threadGroup.activeCount());

        System.out.println("\n线程组信息：\n");
        threadGroup.list();
        System.out.println("\n");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            String groupAndName = "线程组名：" + Thread.currentThread().getThreadGroup().getName()
                    + "； 线程名称：" + Thread.currentThread().getName();

            while (true) {
                System.out.println(groupAndName);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
