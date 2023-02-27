package com.thread;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// ReentrantLock 每次只允许一个线程占有锁，实际不需要这么严苛
// ReadWriteLock 读共享，写独占。称为「悲观锁」
// 同一时刻，要么是若干个读线程，要么是一个写线程
public class th_12 {
}


class Counter6{
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private final Lock rlock = rwlock.readLock();
    private final Lock wlock = rwlock.writeLock();
    private int[] counts = new int[10];     // 用于读写的数据

    // 写线程
    public void inc(int index){
        wlock.lock();
        try {
            counts[index]+=1;
        } finally {
            wlock.unlock();
        }
    }

    // 读线程
    public int[] get(){
        rlock.lock();
        try {
            return Arrays.copyOf(counts,counts.length);
        } finally {
            rlock.unlock();
        }
    }

}
