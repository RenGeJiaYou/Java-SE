package com.thread;

import java.util.LinkedList;
import java.util.Queue;

// 使用 wait 和 notify 实现任务的等待和唤醒
// synchronized 让多线程间实现了同步，但是还不够协调
// 多线程协调的原则：当条件不满足时，线程进入「等待」状态；当条件满足时，线程被「唤醒」，继续执行任务。
public class th_9 {
    public static void main(String[] args) {

    }
}

class TaskQueue{
    Queue<String> queue = new LinkedList<>();

    // 添加元素前加锁，完成后解锁
    public synchronized void addTask(String s){
        this.queue.add(s);
        this.notify();  // 满足条件后，唤醒在等待 this 锁的线程
    }

    public synchronized  String getTask() throws InterruptedException {
        // while 的意图为：循环检测 queue 的状态，为空就继续等待
        while (queue.isEmpty()) {
            this.wait();    // this 锁被释放。当条件不满足时，本线程等待。
            // 重新获得锁。线程在等待状态时 wait() 不会返回，而在将来被某个进程唤醒后才会返回。
        }
        return queue.remove();
    }

}
