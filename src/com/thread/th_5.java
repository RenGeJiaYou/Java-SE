package com.thread;

// 线程同步1
// 多个线程同时运行时，调度是由操作系统完成的，程序本身无法安排。这样容易产生数据不一致的问题
//
public class th_5 {
    public static void main(String[] args) throws InterruptedException {
        // 一个例子，展示多个线程同时读写共享变量，会导致数据不一致的问题
//        Thread add = new AddThread();
//        Thread dec = new DecThread();
//        add.start();
//        dec.start();
//        add.join();
//        dec.join();
//        System.out.println(Counter.count);// 应该是0，但每次打印出的值都不一样
        // 结果为 0 的隐含条件是：每一次 Add /Dec 操作都是在前一次操作的正确结果上进行的，即【原子操作】。
        // 这是由于，Java 的 ++ 或 -- 操作在汇编层次实际不是原子操作，而是分为 LOAD -> ADD -> STORE 等
//         ┌───────┐    ┌───────┐
//         │Thread1│    │Thread2│
//         └───┬───┘    └───┬───┘
//             │            │
//             │ILOAD (100) │
//             │            │ILOAD (100)
//             │            │IADD
//             │            │ISTORE (101)
//             │IADD        │
//             │ISTORE (101)│
//             ▼            ▼
        //
        // 向想对代码进行原子操作，需要加锁。Java 中的锁是 synchronized 关键字
        Thread add1 = new AddThread1();
        Thread dec1 = new DecThread1();
        add1.start();
        dec1.start();
        add1.join();
        dec1.join();
        System.out.println(Counter.count);// 0

        // synchronized(){...} 会在进入代码块时加锁，退出代码块解锁
        // 操作流程：
        //      1.找出修改共享变量的代码部分
        //      2.选择一个共享实例做锁,本例为 Counter 类的一个静态常量,类型为 Object
        //      3.使用 synchronized(lockObject) { ... }
        // 优点：保证多线程读写共享变量的正确性
        // 缺点：无法并发执行，加解锁也要花费时间。这些都带来了性能下降
    }
}

class Counter {
    public static final Object lock = new Object();
    public static int count = 0;

}

class AddThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter.count++;
        }
    }
}

class DecThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter.count--;
        }
    }
}

class AddThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock) {
                Counter.count++;
            }
        }
    }
}

class DecThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock) {
                Counter.count--;
            }
        }
    }
}

