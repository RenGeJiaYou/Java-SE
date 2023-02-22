package com.base.abs;
//面向抽象编程：尽量引用高层类型，避免引用实际子类型的方式
public class Main {
    public static void main(String[] args) {
        // 抽象类 Person 约定了规范，任何人都可以按自己的需求实现超类。
        // 超类实例的调用者不必关心其它超类内部的重写函数是怎么实现的，声明一个基类引用变量，即可调用 超类的重写函数。
        Person s = new Student();
        Person c = new Collage();
    }
}

abstract class Person {
    // 声明 abstract ,函数才能只写个签名
    public abstract void run();
}

class Student extends Person {
    @Override
    public void run() {
        System.out.println("Student run");
    }
}

class Collage extends Student {
    @Override
    public void run() {
        System.out.println("Collage run");
    }
}

// 抽象类进一步，如果一个抽象类没有字段，只有方法。应当改写为接口
