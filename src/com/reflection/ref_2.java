package com.reflection;

import java.lang.reflect.Field;

// 访问字段
public class ref_2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class c = Student.class;

        // 一、获取字段
        System.out.println(c.getField("name"));    // 指定字段名返回(允许上溯到基类中定义的字段)。依次在本类->其实现的接口->其继承的基类中查询
        System.out.println(c.getDeclaredField("score")); // 指定字段名返回
        System.out.println(c.getDeclaredField("grade")); // 获取private字段"grade":

        // 二、获取字段类型信息
        Field[] fs = c.getFields();
        for (Field field : fs) {
            System.out.println(field.getName());        //字段名
            System.out.println(field.getType());        //字段类型
            System.out.println(field.getModifiers());   //字段修饰符,用 int 表示,不同的bit表示不同的含义。
            System.out.println();
        }

        // 三、get/set字段值
        Object p = new Person("xiaomin");       // 创建对象
        Class cls = p.getClass();                     // 获知该对象所属的类
        Field f = cls.getField("name");         // 获知该类所含的字段
        f.setAccessible(true);                        // 如果访问的字段是 private ,需添加该语句以正常使用

        Object value = f.get(p);                      // 字段调用 get() 传入实例对象，获知返回值（静态字段可以不传参）
        System.out.println(value);
        System.out.println(value.getClass());
        System.out.println();

        f.set(p, "Freeman");
        System.out.println(((Person) p).name);

        // "f.setAccessible(true);"的存在让 Java 访问控制有什么意义？
        // 1.正常情况下，使用 obj.field 的方式访问字段是受制于访问控制符(public,protected,private)的。而反射是提供给工具或底层框架来使用的一种非常规手段
        // 2.setAccessible(true) 有时会失败。当 JVM 运行期存在 SecurityManager ，那么它会根据规则进行检查，有可能阻止 setAccessible(true)

        //四、设置字段值
    }
}

class Student extends Person {
    public int score;
    private int grade;

    public Student(String name) {
        super(name);
    }
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}
