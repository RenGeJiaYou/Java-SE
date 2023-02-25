package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock （可重入锁）是 synchronized 的更好的替代品
// synchronized 太重了，而且获取时必须一直等待，没有额外的尝试机制
public class th_10 {
}

class Counter5 {
    private final Lock lock = new ReentrantLock();
    private int count;

    // 原有的 synchronized 写法
    public void add_old(int n) {
        synchronized (this) {
            count += n;
        }
    }

    // ReentrantLock 写法,需要手动处理异常
    // 基本就和 Go 很像了，临界区代码前后是 Lock / Unlock
    // 和 synchronized 不同的是，ReentrantLock 可以尝试获取锁：
    public void add(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }
}