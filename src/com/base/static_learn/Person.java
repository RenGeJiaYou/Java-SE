package com.base.static_learn;

public class Person implements Animal {
    int a;
    int b;
    int c;


    public static int getCount() {
        return count;
    }

    static int count;

    public Person(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        count++;
    }

    // 静态变量通过类型名去访问，而不是实例对象。如果一定要通过实例对象，那实际上是编译器根据实例类型自动转换为 类名.静态字段 来访问静态对象。
    public static int num = 100;

    //
    static void say() {
        System.out.println("balabala");
    }
}
