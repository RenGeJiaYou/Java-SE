package com.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// Atomic 位于 java.util.concurrent.atomic , 用于执行原子操作
// Atomic 并不是通过加锁实现线程安全的，而是采用了 CAS (Compare and Set) 原理
// CAS是指，在这个操作中，
//      如果 AtomicInteger 的当前值是 prev，  那么就更新为next，返回true。
//      如果 AtomicInteger 的当前值不是 prev，就什么也不干，返回false。
// 通过 CAS 操作并配合 do ... while 循环，即使其他线程修改了 AtomicInteger 的值，最终的结果也是正确的。
public class th_16 {
    public static void main(String[] args) {
        // AtomicInteger
//        增加值并返回新值：     int addAndGet(int delta)
//        加1后返回新值：       int incrementAndGet()
//        获取当前值：          int get()
//        用CAS方式设置：       int compareAndSet(int expect, int update)


    }



    // CAS 的大致流程
    public int incrementAndGet(AtomicInteger var) {
        int prev, next;
        do {
            prev = var.get();
            next = prev++;
        }while(! var.compareAndSet(prev,next));
        return next;
    }
}

// 编写多线程安全的全局唯一ID生成器
class IdGenerator {
    AtomicLong var = new AtomicLong(0);

    public  long getNextId(){
        return var.incrementAndGet();
    }

}



