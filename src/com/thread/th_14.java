package com.thread;

import java.util.UUID;
import java.util.concurrent.Semaphore;

// Semaphore 保证了同一时刻最多有 N 个线程能访问
// 请求用 acquire(int n)
// 释放用 release()
public class th_14 {
}

class AccessLimitControl {
    // 例如数据库最多保持 10 个连接
    final Semaphore semaphore = new Semaphore(10);

    public String access() throws InterruptedException {
        // 如果未超过请求数量，将成功分配锁；否则将等待
        semaphore.acquire();
        try {
            // do something
            return UUID.randomUUID().toString();
        } finally {
            semaphore.release();
        }
    }
}
