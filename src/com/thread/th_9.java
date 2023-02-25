package com.thread;

import java.util.LinkedList;
import java.util.Queue;

// 使用 wait 和 notify
// synchronized 让多线程间实现了同步，但是还不够协调
public class th_9 {
    public static void main(String[] args) {

    }
}

class TaskQueue{
    Queue<String> queue = new LinkedList<>();

    // 添加元素前加锁，完成后解锁
    public synchronized void addTask(String s){
        this.queue.add(s);
    }

    public synchronized  String getTask() {
        // while 的意图为：循环检测 queue 的状态，为空就继续等待
        // 但实际上，while() 永远不会退出，因为在 getTask() 已经得到了当前对象的 this 锁。其它线程无法调用 addTask() 方法，因为 addTask() 也要调用 this 锁
        while (queue.isEmpty()) {
        }
        return queue.remove();
    }

}
