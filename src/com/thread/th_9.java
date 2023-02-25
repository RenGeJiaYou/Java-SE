package com.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 使用 wait 和 notify 实现任务的等待和唤醒
// synchronized 让多线程间实现了同步，但是还不够协调
// 多线程协调的原则：当条件不满足时，线程进入「等待」状态；当条件满足时，线程被「唤醒」，继续执行任务。
public class th_9 {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue q = new TaskQueue();
        ArrayList<Thread> ts = new ArrayList<>();

        // 5 个执行 get 的线程
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    // 执行task
                    while (true) {
                        try {
                            String s = q.getTask();     // 不满足条件时 wait()
                            System.out.println("execute task: " + s);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            t.start();
            ts.add(t);
        }

        // 执行 add 的线程
        Thread add = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 放入 task
                String s = "t-"+Math.random();
                System.out.println("add task "+ s);
                q.addTask(s);                       // 完成后唤醒所有 wait() 的线程。但最终只有（随机的）一个重新获得锁
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread t:ts){
            t.interrupt();
        }
    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    // 添加元素前加锁，完成后解锁
    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();  // 满足条件后，唤醒在等待 this 锁的线程
    }

    public synchronized String getTask() throws InterruptedException {
        // while 的意图为：循环检测 queue 的状态，为空就继续等待
        while (queue.isEmpty()) {
            this.wait();    // this 锁被释放。当条件不满足时，本线程等待。
            // 重新获得锁。线程在等待状态时 wait() 不会返回，而在将来被某个进程唤醒后才会返回。
        }
        return queue.remove();
    }
    // 关于使用 while 而非 if 判断条件的原因：
    // 如果是用 while 作判断，每次 wait 被 notify 唤醒后，会继续循环，判断当前队列是否为空，
    // 而 if 做判断时，如果 wait 被唤醒，此时 if 已经被判断（动作1）过了，就会直接继续执行后面的代码（动作2），动作1和2间有一小段时间，可能被其它线程又取空队列了，那么此时该线程就报 nosuchelement exception。
}
