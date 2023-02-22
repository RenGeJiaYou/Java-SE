package com.reflection;

// 获取继承关系：当获取了一个 Class 类型，如何获取它的父类类型？
public class ref_5 {
    public static void main(String[] args) {
        // 一、获取父类的Class
        // 使用 Class 实例的方法 getSuperclass()
        Class i = Integer.class;
        Class n = i.getSuperclass();
        System.out.println(n);                  // class java.lang.Number

        Class o = n.getSuperclass();
        System.out.println(o);                  // class java.lang.Object
        System.out.println(o.getSuperclass());  // null


        // 二、获取 interface
        Class[] is = i.getInterfaces(); // 获取 当前类 直接实现的接口类型。而不包括父类实现的接口类型
        for (Class ii : is) {
            System.out.println(ii); // interface java.lang.Comparable
        }

        // 三、判断 能否向上转型
        Boolean b1 = Integer.class.isAssignableFrom(Integer.class);
        Boolean b2 = Number.class.isAssignableFrom(Integer.class);
        Boolean b3 = Object.class.isAssignableFrom(Integer.class);
        Boolean b4 = Double.class.isAssignableFrom(Integer.class);
        System.out.println("b1: " + b1); // b1: true
        System.out.println("b2: " + b2); // b2: true
        System.out.println("b3: " + b3); // b3: true
        System.out.println("b4: " + b4); // b4: false

    }
}
