package com.reflection;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 反射访问构造函数
public class ref_4 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 常规创建实例的方法
        Person p =new Person("aim");
        // 反射方法.但只能调用 public 的无参构造函数
//        Person p2 = Person.class.newInstance();


        // 鉴于 newInstance() 方法的局限性。 Java 反射提供 Constructor 对象以调用各种类型的构造函数
        // 一、获取构造函数 Integer(int)、Integer(String)
        Constructor c1 = Integer.class.getConstructor(int.class);
        Constructor c2 = Integer.class.getConstructor(String.class);

        // 二、调用构造函数
        Integer n1 = (Integer) c1.newInstance(123);
        Integer n2 = (Integer) c2.newInstance("456");
        System.out.println(n1);
        System.out.println(n2);

        // 总结
        // 某个类型对应的 Class 获取构造函数的方法(不涉及父类):
//        getConstructor(Class...)：             获取某个 public 的 Constructor；
//        getDeclaredConstructor(Class...)：     获取某个 Constructor；
//        getConstructors()：                    获取所有 public 的Constructor；
//        getDeclaredConstructors()：            获取所有 Constructor。

    }
}
