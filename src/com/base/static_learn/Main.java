package com.base.static_learn;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(1, 1, 1);
        Person p2 = new Person(2, 2, 2);
        Person p3 = new Person(3, 4, 3);

        System.out.println(Person.num);
        Person.say();
        System.out.println(Person.getCount());// 3
    }
}

interface Animal{
    public static final int num = 0;
    // 和下面完全等同,编译器会自动加上  public static final
    int num1 = 0;
}

