## synchronized

多线程在对临界区数据同时涉及状态变更的操作的时候就会出现数据安全问题，为了保证数据访问操作结果的正确性，Java提供显式的互斥操作，即通过锁来实现线程并发同步互斥（其实说互斥也是一种协作机制，互斥不是目的，最终共同完成目标任务才是目的，只不过过程中出现需要协调一致的方式）。

## 1 <span id="head85">synchronized</span>

* 作用

实现线程间同步，即对同步代码块加锁，使得同一时刻只能只能有一个线程进程进入同步代码块，换句话说实现同步代码块线程访问串行化。

* 特点

//TODO

* 方式

作用于实例方法

```java
package com.skylaker.sync;

import org.junit.jupiter.api.Test;

/**
 * synchronized 修饰实例方法
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/7 10:21 PM
 */
public class SynchronizedService {
    private int sum = 0;

    @Test
    public void test() throws InterruptedException {
        MyTask myTask = new MyTask(10000);

        Thread thread1 = new Thread(myTask);
        Thread thread2 = new Thread(myTask);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("计算结果：" + sum);
    }

    class MyTask implements Runnable {
        private int num = 0;

        MyTask(int num){
            this.num = num;
        }

        public void run() {
            for(int i = 0; i < num; i++){
                add();
                Thread.yield();
            }
        }

        // 修饰实例方法
        private synchronized void add(){
            sum++;
        }
    }
}
```

作用于代码块

```java
package com.skylaker.sync;

import org.junit.jupiter.api.Test;

/**
 * synchronized 修饰代码块
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/7 10:21 PM
 */
public class SynchronizedService2 {
    private  int sum = 0;
    private Object lock = new Object();

    @Test
    public void test() throws InterruptedException {
        MyTask myTask = new MyTask(10000);

        Thread thread1 = new Thread(myTask);
        Thread thread2 = new Thread(myTask);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("计算结果：" + sum);
    }

    class MyTask implements Runnable {
        private int num = 0;

        MyTask(int num){
            this.num = num;
        }

        public void run() {
            for(int i = 0; i < num; i++){
                add3();
                Thread.yield();
            }
        }

        // 修饰代码块：指定实例对象锁
        private void add2(){
            synchronized (lock){
                sum++;
            }
        }

        // 修饰代码块：指定类字节码对象锁
        private void add3(){
            synchronized (MyTask.class){
                sum++;
            }
        }
    }
}
```

作用于静态方法

```java
package com.skylaker.sync;

import org.junit.jupiter.api.Test;

/**
 * synchronized 修饰静态方法
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/7 10:21 PM
 */
public class SynchronizedService3 {
    private static int sum = 0;

    @Test
    public void test() throws InterruptedException {
        MyTask myTask = new MyTask(10000);

        Thread thread1 = new Thread(myTask);
        Thread thread2 = new Thread(myTask);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("计算结果：" + sum);
    }

    static class MyTask implements Runnable {
        private int num = 0;

        MyTask(int num){
            this.num = num;
        }

        public void run() {
            for(int i = 0; i < num; i++){
                add3();
                Thread.yield();
            }
        }

        // 修饰静态方法
        private synchronized static void add3(){
            sum++;
        }
    }
}
```

* 注意点
  其实不管何种方式，对于不同线程操作同一个临界区，保证用的是同一把锁即可。