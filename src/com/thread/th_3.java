package com.thread;

// 中断线程
// 试想一种情景：用户因为下载任务慢，不耐烦地取消了下载任务。
// 代码要为此实现中断功能，Java 的其他线程中对目标线程调用 interrupt() 方法
public class th_3 {
    public static void main(String[] args) throws InterruptedException {
        // 一、对状态不同的线程调用 interrupt() 【即发送中断请求】，反馈也不同
        // 1.对 运行(Runnable) 状态的线程调用 interrupt()
        // 线程内部通过调用 isInterrupted() 判断自己是否需要中断
//        Thread t1 = new MyThread1();
//        t1.start();
//        Thread.sleep(1);    // 在 main 休眠的 1ms 内，t1 在执行（循环打印 n + “Hello”）
//        t1.interrupt();  // main 线程从休眠中恢复，调用 t1.interrupt() 向 t1 线程发送【中断请求】
//        t1.join();   // 要求主线程等待，直到 t1 结束后再恢复执行   ** join() 是一个连接点，使 join() 语句前执行的一定是 t1 ，join() 后执行的一定是 main
//        System.out.println("end");


        // 2.对 等待(Waiting) 状态的线程调用 interrupt()
        // 该线程的 join() 方法将立即抛出 InterruptedException 异常。
        // 因此，主调线程 t2 只要在调用被调线程 base.join()时，捕获到 join() 所抛出的 InterruptedException 异常，说明有其它进程对 t2 调用了 interrupt()
        Thread t2 = new MyThread2();
        t2.start();
        Thread.sleep(1000);// (3).主线程休眠 1000 ms , 足以执行完「 t2 调用 base -> base 进入无限循环 -> t2 因调用 base.join() 陷入无限等待」的过程
        t2.interrupt();// (4).此时 t2 已经因 base.join() 陷入等待,对 t2 进行中断,t2 内部的 base.join() 语句将立刻抛出 InterruptedException
        t2.join();
        System.out.println("end");

    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        int n = 0;
        // while 语句完成【响应中断】，具体代码为循环检测 isInterrupted()
        while (!isInterrupted()){   // isInterrupted() 只能继承 Thread 类后才能调用父类方法。不能在实现 Runnable 接口（lambda 表达式）中被调用
            n++;
            System.out.println(n+" Hello");
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        Thread base = new BaseThread();
        base.start();
        try {
            base.join();    // (2).base 将无限循环执行下去，join() 会让 MyThread2 线程从这一句开始一直保持 Waiting
        } catch (InterruptedException e) {
            System.out.println("interrupted");//(5).t2 内部的 base.join() 抛出异常，转到 catch 语句。这么设计很好理解，你自己(t2)都要被中断了，自己所等待的其它线程(base)也没必要再等了。
        }
        base.interrupt(); // (6).如果没有这一句，base 线程将在 t2 线程终止后继续无限循环下去。
    }
}

// 故意让 BaseThread 无限循环执行，使 MyThread2 调用 BaseThread 会等待很久
class BaseThread extends Thread{
    @Override
    public void run() {
        int n = 0;
        while(!isInterrupted()){
            n++;
            System.out.println(n+" 次");
            try {
                Thread.sleep(100);  //(1).在响应中断之前，每打印一条，睡眠 100 ms
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}