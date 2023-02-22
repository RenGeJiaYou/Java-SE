package com.thread;
// 守护进程
// Java 应用的线程情况：
// JVM 线程 -> main 线程 -> 其它子线程 -> 全部子线程结束 -> JVM 退出，程序结束
// 如果有一个子线程不退出，JVM 就不会退出
// ※ 问题是：如果就是需要一个无限循环的定时线程（而没有其它线程能关闭该线程），同时仍希望 JVM 能正常退出。那就需要用「守护线程」标记这个线程
// JVM 规定：只要所有非守护线程都执行完毕，无论有没有守护线程，虚拟机都会自动退出。
public class th_4 {
    public static void main(String[] args){
        // 对线程对象调用 setDaemon(true) 建立一个守护线程
        Thread t = new Thread(()->{
            System.out.println("I'm a Daemon Thread");
        });
        t.setDaemon(true);
        t.start();
    }

}
