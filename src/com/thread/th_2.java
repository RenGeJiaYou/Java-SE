package com.thread;
// 线程的状态：
//          Runnable        运行
//          Blocked         阻塞
// New ->   Waiting         等待                  -> Terminated
//          Timed Waiting   因 sleep() 而等待
//
// 线程的终止：
// - 正常终止：run() 执行到 return 语句返回
// - 意外终止：run() 因未捕获的异常导致异常终止
// - 强制终止：对一个线程实例调用 stop() (强烈不推荐)
public class th_2 {
    public static void main(String[] args) throws InterruptedException {
        // 1. t.join() 安排线程的先后执行
        Thread t = new Thread(()->{
            System.out.println("Thread Hello");
        });
        System.out.println("Main start");
        t.start();
        t.join();   // 要求主线程等【本线程结束后】再调用.如果 t 线程已经结束，对实例 t 调用join()会立刻返回
        System.out.println("Main end");
    }
}
