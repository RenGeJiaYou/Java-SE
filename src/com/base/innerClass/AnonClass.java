package com.base.innerClass;

public class AnonClass {
    public static void main(String[] args) {
        Outer1 o = new Outer1("abc");
        o.asyncHello();
    }
}

/*
 * Runnable 是一个不能实例化的接口。
 * 所以 asyncHello() 内实际上是定义了一个实现 Runnable 接口的匿名类，实例化后再用 Runnable 类型变量引用
 * 编译后，Outer 类-> outer.class ;其内部的匿名类被命名为outer$1、outer$2.....
 * */
class Outer1 {
    private String name;

    public Outer1(String name) {
        this.name = name;
    }

    // 通过继承自接口的方式构造匿名类
    void asyncHello() {
        Runnable r = new Runnable() {
            // 实现必要的抽象方法
            @Override
            public void run() {
                System.out.println("Hello" + Outer1.this.name);
            }
        };
        new Thread(r).start();
    }
}



