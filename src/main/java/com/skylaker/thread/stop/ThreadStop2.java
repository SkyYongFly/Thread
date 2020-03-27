package com.skylaker.thread.stop;

/**
 * 线程直接stop产生问题场景模拟
 *
 * @author skylaker2019@163.com
 * @version V1.0 2019/7/20 9:53 PM
 */
public class ThreadStop2 {
    private static User user  = new User();

    public static void main(String[] args){
        // 开启读线程不停的读
        new ReadThread().start();

        // 开启写线程不停的写
        while(true){
            WriteThread writeThread = new WriteThread();
            writeThread.start();

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeThread.stop();
        }
    }

    /**
     * 读线程：读取User对象的age和name，当不一致的时候输出
     */
    static class ReadThread extends Thread {
        @Override
        public void run() {
            while (true){
                synchronized (user){
                    if(user.getAge() != Integer.parseInt(user.getName())){
                        System.out.println("当前User的age和name不一致，age："
                                + user.getAge()
                                + ", name: "
                                + user.getName());
                    }
                }

                // 临时让出资源避免读线程一直持有锁对象，
                // 写线程无法操作，观察不出现象
                Thread.yield();
            }
        }
    }

    /**
     * 写线程：不停的设置User的 age、name值，且值一致（忽略类型）
     */
    static class WriteThread extends Thread {
        @Override
        public void run() {
            while (true){
                synchronized (user){
                    int value = (int) (System.currentTimeMillis() / 1000);
                    user.setAge(value);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    user.setName(String.valueOf(value));
                }

                Thread.yield();
            }
        }
    }

    static class User {
        private int age;

        private String name;

        User() {
            // 初始化设值，避免读线程刚启动疯狂读取没有值报错
            age = 1;
            name = "1";
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
