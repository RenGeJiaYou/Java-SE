package com.test;

import com.test.ploy.Income;
import com.test.entity.Person;
import com.test.entity.Student;

public class Main {
    public static void main(String[] args) {

        // 打印水仙花数
        for (int i = 100; i < 1000; i++) {
            int a = i % 10;
            int b = (i / 10) % 10;
            int c = i / 100;

            if (a * a * a + b * b * b + c * c * c == i) {
                System.out.println(i);
            }
        }

        //打印九九乘法表
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (j > i) break;
                System.out.printf("%d × %d = %2d  ", i, j, i * j);
            }
            System.out.println();
        }

        //斐波那契数列 1,1,2,3,5,8,13,21
        int target = 20, result = 0;
        int a = 1, b = 1, tmp;
        for (int i = 1; i < target; i++) {
            tmp = a + b;
            a = b;
            b = tmp;
        }
        result = a;
        System.out.println(result);


        /*
         * 继承
         * */
        Person p = new Person("asd", 12, "male");
        p.name = "Fuck Java";
        p.age = 18;
        p.Hello();
        p.sum(2, 3);
        p.sum(2.2, 3.3);

        p.info = "789";
        Person p2 = new Person("eqw", 123, "asda");
        System.out.println(p2.info); // p2 的静态变量 info 的值正是被 p 所修改的。

        // 向上转型：子类的属性和方法比父类更多。因此转型时抛弃新增部分即可。
        // Student s = new Person("wer",12,"456");                                 //不能将父类实例传给子类变量
        Person ps = new Student("123", 123, "123", 123);       //可以将子类实例传给父类变量

        // 向下转型
        Person ps1 = new Student("123", 123, "123", 123);
        Person ps2 = new Person("123", 123, "123");
        Student s1 = (Student) ps1;
//        Student s2 = (Student)ps2;  // 把一个父类类型强制转为子类类型称为向下转型。系统不确定如何对新增的子类属性和方法赋值，因此将报错。

        // instanceof 判断一个变量所指向的实例是否是（指定类型，或者这个类型的子类）
        System.out.println(ps1 instanceof Person); //true
        System.out.println(ps1 instanceof Student); //true

        System.out.println(ps2 instanceof Person);//true
        System.out.println(ps2 instanceof Student);//false ps2指向一个 Person 实例，显然不属于 Student 及其子类

        /*
         * 多态
         * overload 和 override 的区别
         * override(重写)：
         *       子类中 override 的函数的名字、形参列表、返回值和父类完全一样。是对原有函数的内部语句进行重写
         *
         * overload(重载)：
         *       子类中 override 的函数的名字和父类一样。是新的函数，只是同名。
         * */
        ps1.Hello();    // "Student Hello" ps1是一个 Person 引用变量，但指向一个 Student 实际变量。因此执行 Student 的类方法
        ps2.Hello();    // "Hello"


    }
}