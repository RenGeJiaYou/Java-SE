package com.thread;

// 线程同步2
public class th_6 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[]{new AddStudentThread(), new DecStudentThread(), new AddTeacherThread(), new DecTeacherThread()};
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            t.join();
        }
        System.out.println(Counter1.studentCount);
        System.out.println(Counter1.teacherCount);
        // Student 和 Teacher 各自对自己的共享变量操作，这两者之间是同步的,可以用不同的 Lock 。
        // 同一个共享变量的加&减操作是互斥的，必须用同一个 Lock。

        // JVM 定义了几种原子操作，单条原子操作不需要同步
        //  1.基本类型(除去 double / long)的赋值
        //  2.引用类型赋值 如 List<String> list = anotherList、数组赋值

            }
}

class Counter1 {
    // 能划分多少个可以并发操作的、操作无关的组，就有多少个 Lock
    public static final Object lock_stu = new Object();
    public static final Object lock_tea = new Object();

    public static int studentCount = 0;
    public static int teacherCount = 0;
}

class AddStudentThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter1.lock_stu) {
                Counter1.studentCount ++;
            }
        }
    }
}

class DecStudentThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter1.lock_stu) {
                Counter1.studentCount --;
            }
        }
    }
}

class AddTeacherThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter1.lock_tea) {
                Counter1.teacherCount ++;
            }
        }
    }
}

class DecTeacherThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter1.lock_tea) {
                Counter1.teacherCount --;
            }
        }
    }
}