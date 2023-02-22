package com.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// 为什么需要覆写 equals()
// 如果不这么做，当 待比较对象 和 集合已有对象值 相同时，基于 equals() 的 contains()、indexOf() 无法正常执行。


// 自定义类中， equals() 的编写流程：
// 1.先确定实例“相等”的逻辑，即哪些字段相等，就认为实例相等；
// 2.用 instanceof 判断传入的待比较的 Object 是不是当前类型，如果是，继续比较，否则，返回 false；
// 3.对引用类型用 Objects.equals() 比较，对基本类型直接用 == 比较。

public class co_2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C");     //注意 asList() 返回的静态内部类不支持增删元素，因此add()、remove() 将报错

        // 传入不同的 String 实例，contains(),indexOf() 仍然生效
        // 因为这两个方法底层调用了 Obejct 的类方法 equals() 判断两个值是否相等，不关心是不是同一个实例。
        System.out.println(list.contains(new String("C"))); // 仍然是 true
        System.out.println(list.indexOf(new String("C"))); // 仍然是 2

        // 若自定义类中不覆写 equals() ，该类实例组成的集合无法正确使用 contains()、indexOf()
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person("Zhang"));
        personList.add(new Person("Li"));
        personList.add(new Person("Sun"));

        System.out.println(personList.contains(new Person("Li")));  // 未 override Object 类的方法 equals(),因此查不到已有的 Person("Li")


    }
}

class Person {
    String name;
    public int age;


    public Person(String name) {
        this.name = name;
    }

    // 覆写 equals() （IDEA 使用 Ctrl + o 快速添加可覆写的方法）
    // 设计:              对于 Person 类来说，“equals”意味着 name 和 age 字段都相等.
    // 引用类型和基本类型:   String 作为核心类已有 equals() 方法；基本类型直接使用 ==
    // null 值:          应当有空值检测
    @Override
    public boolean equals(Object o) {
//         版本一
//        if (o instanceof Person){
//            Person p = (Person)o;
//            return this.name.equals(p.name) && this.age == p.age;
//        }


//          版本二 添加 null 检测
//        if (o instanceof Person) {
//            Person p = (Person) o;
//            boolean nameEquals = false;
//            if (this.name == null && p.name == null) nameEquals = true;
//
//            if (this.name != null) {
//                nameEquals = this.name.equals(p.name);
//            }
//
//            return nameEquals && this.age == p.age;
//        }

//        版本三 简化引用类型的比较,使用 Objects 的静态方法
        if (o instanceof Person) {
            Person p = (Person) o;
            return Objects.equals(this.name, p.name) && this.age == p.age;
        }
        return false;
    }


}