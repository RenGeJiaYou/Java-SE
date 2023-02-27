package com.thread;

import java.util.concurrent.locks.StampedLock;

// StampedLock 把 读锁 细分为乐观读和悲观读。乐观锁和悲观锁的区别是，读的过程中也允许【获取写锁后写入】
// 这可能导致读到的数据不一致，因此需要一些额外的代码校验
// “乐观”锁乐观地估计读取过程中大概率不会有写入，在牺牲一部分安全性的同时，并发执行效率更高了。
//
//  不过，这和 只加了写锁 有什么区别？
public class th_13 {
}

class Point {
    private final StampedLock stampedLock = new StampedLock();

    private double x;
    private double y;

    // 写线程
    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();   // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);     // 释放写锁
        }
    }

    // 读线程
    public double distance() {
        long stamp = stampedLock.tryOptimisticRead();    // 获取乐观读锁
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        double currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double currentY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)

        if (!stampedLock.validate(stamp)) {             // validate 可以校验乐观读锁后是否有其它写锁发生.进入代码块表示已发生写操作，换用悲观锁
            stamp = stampedLock.readLock();             // 获取悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);          // 释放悲观读锁
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // StampedLock 把读锁进一步划分为 乐观锁和悲观锁。如果写入的概率不高，程序在绝大部分情况下可以通过乐观读锁获取数据，极少数情况下使用悲观读锁获取数据。
    // StampedLock 是不可重入锁，同一个进程只能获取该锁 1 次
}
