package com.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock （可重入锁）是 synchronized 的更好的替代品
// synchronized 太重了，而且获取时必须一直等待，不能选择【在等待一定时间后放弃】
// ReentrantLock 必须先获取到锁，再进入 try {...} 代码块，最后使用 finally 保证释放锁；
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
    public void add(int n) throws InterruptedException {
        if(lock.tryLock(1, TimeUnit.SECONDS)){  // 尝试获取锁的时候，最多等待1秒。如果1秒后仍未获取到锁，tryLock()返回false，程序放弃等待获取锁，就可以做一些额外处理，而不是无限等待下去。
            try {
                count += n;
            } finally {
                lock.unlock();
            }
        }

    }
}