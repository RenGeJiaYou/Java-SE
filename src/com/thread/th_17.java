package com.thread;

import java.util.concurrent.*;
// ※ 线程池在多线程日常开发中最常用
//
// 线程池 ExecutorService 接口
// 频繁的创建/销毁进程非常占用性能
// 如果可以复用一组线程，在当前线程执行完任务后，不把它销毁，而是给它另外的新任务。这样可以减少进程的频繁创建和销毁。
// 因此我们需要一个实体，负责大量接收小任务并分发处理。
// 线程池内部维护一组空闲线程，如果有新任务就分配给其中之一；如果所有线程都处于忙碌状态，任务要么进入等待队列，要么增设一个新线程
//
// 线程池有多个实现类
// FixedThreadPool：     线程数固定的线程池；
// CachedThreadPool：    线程数根据任务动态调整的线程池；
// SingleThreadExecutor：仅单线程执行的线程池。
public class th_17 {
    public static void main(String[] args) {
        // 一、基本操作流程
        // 1.创建固定大小的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();

        // 2.提交任务
        for (int i = 0; i < 8; i++) {
            pool.submit(new MyTask("" + i));
        }

        // 3.关闭线程池
        pool.shutdown();

        // Output:
//        task start: 1
//        task start: 0
//        task start: 3
//        task start: 2
//        task start: 4
//        task end: 2
//        task end: 3
//        task end: 1
//        task end: 0
//        task end: 4
//        task start: 7
//        task start: 6
//        task start: 5
//        task end: 6
//        task end: 5
//        task end: 7
        // 线程是按给定的 5 个执行，剩下的任务等待。

        // 二、如果想让线程池的大小限制在4～10个之间动态调整，可参考 newCachedThreadPool 的源码
        ExecutorService service = new ThreadPoolExecutor(4, 10,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        // 三、定期反复执行的线程 ScheduledThreadPool
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);

        // 1.提交一次性任务
        ses.schedule(new MyTask("一次性任务"), 1, TimeUnit.SECONDS);

        // 2.任务在 2 秒后开始执行定时任务，以固定的每 3 秒执行。          -> 前后两次任务的开始时间间隔 3 秒
        ses.scheduleAtFixedRate(new MyTask("2 秒后开始每 3 秒执行一次"), 2, 3, TimeUnit.SECONDS);

        // 3.任务在 2 秒后开始执行定时任务，每次在上一次执行完 3 秒后执行    -> 前一次任务的结束时间 和 后一次任务的开始时间间隔 3 秒
        ses.scheduleWithFixedDelay(new MyTask("两次任务间间隔 3 秒"), 2, 3, TimeUnit.SECONDS);

        ses.shutdown();

        // Q1：在 FixedRate 模式下，假设每秒触发，如果某次任务执行时间超过 period 秒，后续任务会不会并发执行？
        // A1：首先，任务的执行是可以超时的。一个任务超时才执行完后，下一个任务将立即开始（追赶时差），直到恢复正常的时间-任务序列
        //
        // Q2：任务抛出异常？后续任务会停止吗？
        // A2：如果能被 catch ，后续继续执行；否则线程将从 Running 强制进入 Waiting 状态。另外，抛出「错误」线程也将被迫停止。
    }
}

class MyTask implements Runnable {
    private final String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("task start: " + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("task end: " + name);
    }
}
