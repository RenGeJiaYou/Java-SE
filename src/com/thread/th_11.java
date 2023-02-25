package com.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Condition 可以配合 ReentrantLock , 实现类似 synchronized + wait() / notify() 的线程协调的效果
// Condition                signal()        signalAll()     await()
// synchronized 的锁对象      notify()        notifyAll()     wait()
public class th_11 {
    public static void main(String[] args) {

    }
}

class TaskQueues {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            this.queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while(queue.isEmpty()){
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}
