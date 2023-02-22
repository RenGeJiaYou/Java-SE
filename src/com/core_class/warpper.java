package com.core.str;

/*
 * 基本类型：byte，short，int，long，boolean，float，double，char
 * 引用类型：所有class和interface类型
 * 若想让一个基本类型像引用类型那样调用，使用 包装类型
 * */
public class warpper {
    public static void main(String[] args) {
        // 1.Interger 包装类和 int 基本类型相互调用。
        Interger n = new Interger(33);
        int n2 = n.intValue();
        Interger n3 = null;

        // 2.Java 核心类自带的基本类型的包装：
        Long l = null;
        l = new Long(385579752);

        // 3.JVM 会自动在 基本类型 和 对应包装类型间转换。比如可以直接赋值(Auto Boxing)：
        Integer i = 54;
        // 或反过来 Auto Unboxing:
        int i2 = new Integer(44);

        // 不过，装箱和拆箱会影响代码的执行效率
        // 4.包装类型都是 final 修饰的不变类。
        // 5.包装类型实例在比较时只能用equals(), 不可用 == 操作符
        // 6.valueOf() 具备缓存功能，当频繁请求同一个值时返回同一个实例对象，相较于构造函数有更好的性能。
        Integer s = Integer.valueOf( 4);

        // 7.另一个使用包装类的好处是，有很多现成的方法可以用
        // 7-1 字符串转换为数字，类比 Go 的 Atoi
        int x = Integer.parseInt("538");
        int y = Integer.parseInt("10000",2);//参数 radix 表示将字符串视作几进制来转换
        System.out.println(y);
        // 7-2 将数字转为字符串，类比 Go 的 Itoa
        String str = Integer.toString(222,2);
        System.out.println(str);
    }
}

// "Interger"是错别字,正好和 Java 原生类区别开
class Interger {
    private int value;

    public Interger(int value) {
        this.value = value;
    }

    public int intValue() {
        return this.value;
    }
}