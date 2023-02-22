package com.thread;

// 可重入锁 & 死锁
// 可重入锁：对同一个线程，获取到一个锁后还能继续获取同一个锁（因为内部函数调用）
// 因为可重入，synchronized 不仅要记录是否加锁，也要记录加了几次锁。
// 每获取一次锁，记录 +1，每退出 synchronized 块，记录 -1 ，减到 0 的时候，才会真正释放锁
public class th_8 {
    public static void main(String[] args) {
        Counter3.add(10);

        // 死锁的一个例子

    }
}

public class Counter3 {
    public static final Object LockA = new Object();
    public static final Object LockB = new Object();

    private int count = 0;
    private int another = 0;

    public synchronized void add(int n) {   //  main 线程第一次获得锁
        if (n < 0) {
            dec(n); // main 线程第二次获得锁
        } else {
            count += n;
        }
    }

    public synchronized void dec(int n) {
        count -= n;
    }


    // 以下两个函数是死锁的一个实例.由于在各自占有一个锁以后请求对方的锁，导致无限等待
    // 修复：使请求资源的顺序一致
    public void abc() {
        synchronized (LockA) {
            count += 5;
            synchronized (LockB) {
                another += 10;
            }
        }
    }

    public void xyz() {
        synchronized (LockA) {
            count -= 15;
            synchronized (LockB) {
                another -= 6;
            }
        }
    }
}
