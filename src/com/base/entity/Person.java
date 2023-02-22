package com.base.entity;

/*
 * 作为优化手段，java 中的 class 只在需要时加载。“需要时”指的是：
 *   1.创建实例时
 *   2.子类创建实例时
 *   3.访问或修改静态变量时
 *   4.调用静态方法时
 *   5.反射
 * */
public class Person {
    public String name;
    public int age;
    public String sex;
    private short cash;
    short car;
    protected String house;

    // 静态变量,所有实例共同拥有、修改的唯一变量。一个实例修改后，对其它实例是可见的
    static public String info;



    public Person(String name, int age, String sex) {
        System.out.println("构造函数执行ing");
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    // 先于构造函数执行的代码块
    {
        System.out.println("类中的代码块中的语句将在 new 实例时执行,并且先于构造函数执行");
    }

    // 成员函数
    public void Hello() {
        System.out.println("hello");
    }

    // 成员变量的赋值
    void setName(String name) {
        this.name = name;
    }

    // 方法的重载:允许出现同名函数，但返回值、形参列表应当不同。只变动返回值类型是不行的
    public int sum(int a, int b) {
        return a + b;
    }

    public double sum(double a, double b) {
        return a + b;
    }

    // 静态方法.由于是类的方法，不能调用任何实例相关的成员变量和成员函数。即，静态方法内不能出现 ‘this’ 字样
    static void func() {
        System.out.println("static func");
        System.out.println();
    }

    // 静态代码块
    static {
        System.out.println("静态代码块");
    }
}
