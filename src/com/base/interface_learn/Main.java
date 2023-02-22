package com.test.interface_learn;

// 接口是进一步的抽象类，连实例字段都不能有.是一组方法签名
// 1.超类只能继承自一个基类，却能实现实现若干个接口
// 2.超类实现的 override 方法必须为 public
// 3.实现类必须实现接口的所有方法，但 default 方法可以不必实现就能继承。因此，使用 default 方法可以为接口增加新的方法的同时 而不必修改实现类
// 4.接口可以有自己的静态字段

public class Main {
    public static void main(String[] args) {
    Chinese c = new Chinese();
    c.run();
    }
}

// 接口可以继承另一个接口
interface Animal{
    void move();
}

interface Human extends Animal{
    // 所有方法默认为 public abstract
    void eat();
    String write();
    default void run(){
        System.out.println("run");
    }
}

interface China{
    void liveInAsia();
}

class Chinese implements Human,China {
    String name;

    @Override
    public void move(){
        System.out.println("move");
    }

    @Override
    public void liveInAsia() {
        System.out.println("live in asia");
    }

    @Override
    public void eat(){
        System.out.println("eat");
    }

    @Override
    public String write(){
        System.out.println("书写");
        return "中文";
    }
}
