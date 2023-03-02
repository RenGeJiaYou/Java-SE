package com.thread;

// ThreadLocal 在一个线程中传递同一个对象
// 剩下的部分不太能理解
public class th_21 {
    public static void main(String[] args) {
        //一、currentThread() 获取当前线程
        // 线程名为 Thread-0
        new Thread(()->{
           log("run task..");
        }).start();

        // 线程名为 Thread-1
        new Thread(()->{
            log("print...");
        }).start();

        // 线程名为 main
        log("main end");
    }
    static void log(String s) {
        System.out.println(Thread.currentThread().getName() + " : " + s);
    }
}
