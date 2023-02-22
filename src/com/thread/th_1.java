package com.thread;

// 创建新线程
// Java 中的线程，当然也是一个类
// 不如 Go 的 goroutine 方便
public class th_1 {
    public static void main(String[] args) {
        System.out.println("main start");

        Thread t = new Thread();
        t.start();  // 什么都不做就结束了

        // 让线程执行实际任务有几种方式
        // 方式一：从 Thread 继承一个类，并覆写 run()
        Thread t1 = new MyThread();
        t1.start(); //

        // 方式二：创建 Thread 实例时，传入一个实现了 Runnable 的实例
        Thread t2 = new Thread(new MyRunnable());
        t2.start();

        // 方式二的简化：采用 lambda 的简化写法：
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("传入 lambda 表达式");
        });
        t3.setPriority(10); // 设置优先级，1最低，10最高
        t3.start();

        System.out.println("main end");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("the first thread!");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("implements Runnable");

    }
}
