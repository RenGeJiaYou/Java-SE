package com.reflection;

import java.util.Date;

public class ref_1 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 一、关于反射
        // Java 中除了少数几种基本类型（int,double），其余都是 class
        // 无继承关系的类之间无法赋值
        Number n = new Integer(5);  // √
//    String s = new Double(10.3);    // × 编译报错

        // Java 中，Class 也是一种 class .
        // 在程序执行过程中，JVM 每加载一种 class ,都会为其创建一个 Class 类型的实例（只有 JVM 能这么做）
        // 换言之，JVM 持有的每个 Class 实例都指向一个数据类型（ class 或 interface ）
        // 每个 Class 实例都包含了这个 class 的完整信息,包括类名、所隶属的包名、字段名、方法名、所继承的基类、所实现的接口...
        // 这种通过 Class 类型获取当前 class 本体信息的方式，称为反射

        // 二、获取一个 class 的 Class 实例
        // 1.对于一个类型：通过一个 class 的静态变量 class 获取
        Class c1 = String.class;
        printClassInfo(c1);

        // 2.对于一个实例：通过一个实例方法 getClass()
        Date e = new Date();
        Class c2 = e.getClass();
        printClassInfo(c2);

        //3.对于一个类的完整类名，通过 Class 的静态方法 Class.forName()
        Class c3 = Class.forName("java.lang.Short");
        printClassInfo(c3);

        String[] ss = new String[10];
        printClassInfo(ss.getClass()); // String[] 的类型是 Ljava.lang.String


        // 三、Class 实例比较 （与 instanceof 的差异）
        // instanceof 比较宽松，对有继承关系的一组类都将返回 true ; getClass() 精确地判定是否属于本类
        Integer m = new Integer(123);

        Boolean b1 = m instanceof Integer; // true  实例 m 是 Integer 对象
        Boolean b2 = m instanceof Number;  // true  实例 m 是 Number 的超类的对象

        Boolean b3 = m.getClass() == Integer.class; //true
//        Boolean b4 = m.getClass() == Number.class; // 编译失败

        // 四、根据已知的 Class 信息创建对象。
        // 注意：
        //  1.newInstance() 调用的是 public 的无参构造函数。
        //  2.newInstance() 返回的是 Object 类。
        Date e2 = (Date)c2.newInstance();

    }

    // Class 的若干实例方法，可以返回指定类的各种数据
    static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
        System.out.println();
    }
}
