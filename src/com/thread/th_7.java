package com.thread;

// 同步方法
public class th_7 {
    public static void main(String[] args) {
        // 直接在线程内部调用 synchronized {} 会使编码混乱。
        // 更好的办法是封装到函数内
        Counter2 c1 = new Counter2();
        Counter2 c2 = new Counter2();

        // 对 c1 进行操作的线程
        new Thread(() -> {
            c1.add(4);
        }).start();
        new Thread(() -> {
            c1.dec(4);
        }).start();

        // 对 c2 进行操作的线程,由于内部 synchronized 锁住的是 this 实例，因此各实例间不受影响
        new Thread(() -> {
            c2.add(4);
        }).start();
        new Thread(() -> {
            c2.dec(4);
        }).start();


        // 什么样的类是「线程安全」的
        //  1.被设计为多线程能正确访问的类
        //  2.不变类，例如 String，Integer，LocalDate，其内部所有成员变量均为只读，不可写
        //  3.类似 Math ，只提供静态方法，没有成员变量的类，也是线程安全的
    }
}

class Counter2 {
    private int count = 0;
    private static int flag = 0;

    public void add(int n) {
        synchronized (this) {    // ※ 锁住的是当前实例，意味着多个实例运行时各不影响
            count += n;
        }
    }

    public void dec(int n) {
        synchronized (this) {// 锁住 this
            count -= n;
        } // 解锁
    }

    // 当 synchronized 锁住的是 this 时，实际上可以修改为 成员方法修饰
    public synchronized void mutiply() { // 锁住 this
        count *= 2;
    }// 解锁

    // 当 synchronized 修饰静态方法时（无 this 实例），实际锁住的是 JVM 创建的该类对应的 Class ，即 Counter.class
    public static synchronized void sum() {
        flag = 100;
    }

    public int get() {
        return count;
    }   // 读一个基本类型的变量是原子操作，无需同步。需要同时读取多个变量时需要加锁
}
