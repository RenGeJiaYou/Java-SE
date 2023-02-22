package com.core_class;


// 【现存问题】为什么需要枚举类，而不是直接定义一组常量的集合，原因有两个：
// 1.值的范围不合理，超出了一组常量集合定义的范围.见func1()
// 2.值的类型不合理，定义的常量仍能和其它变量比较，但用途不一致.见func2()
// 以上编译会通过，但不符合语义。
// =======================================================
// 【enum 优点】使用 枚举类 解决了以上两个问题
// 1.Weekday 内实际定义的是 int 类型，可以自由地和其它 int 变量运算，而这种意外的灵活性不是我们想要的。而 Wuxin 本身就是一个类型，不能和其它类型运算。
// 2.使用枚举类时，无法使用定义之外的枚举值
// =======================================================
// 【enum 的比较】见func3()
// 1.枚举类是一种引用类型，能像其它引用类型一样使用 equals() 进行比较
// 2.但是枚举类的每个常量在 JVM 只有唯一值,因此也能用 ==
// =======================================================
// 【enum 类型】
// 枚举类和普通的 class 没有区别。实质是继承自java.lang.Enum 的 final class ，每一个枚举值都是该类的实例，但 Java 语法不支持通过 new 创建，
// 既然枚举值是枚举类的实例，因此枚举值有一些方法可调用。见func4
// =======================================================
// 【enum 赋予特定值】
// 按照从0开始的递增次序，每个 enum 实例默认有一个正整数值。当枚举类内的实例次序改变时，实例的值也会改变。可以显式声明实例的值，使其不随次序的变化而变化。见func5
public class enum_class {
    public static void main(String[] args) {
        Wuxin a = Wuxin.水;
        Wuxin b = Wuxin.火;

        func3(a);
        func3(b);

        func4();

        func5();

    }

    static void func1(int day) {
        if (day == Weekday.FRI || day == 8) {
            System.out.println("错误1");
        }
    }

    static void func2(int task) {
        if (task == Weekday.SAT) {
            System.out.println("错误2");

        }
    }

    static void func3(Wuxin w) {
        if (w.equals(Wuxin.水)) {
            System.out.println("可用 equals()");
        }

        if (w == Wuxin.火) {
            System.out.println("可用 == 操作符");
        }
    }

    static void func4() {
        // 返回常量名
        String soil = Wuxin.土.name();
        System.out.println(soil);

        // 返回常量顺序
        int index = Wuxin.金.ordinal();
        System.out.println(index);
    }

    static void func5() {
        // 返回枚举常量的指定值
        if (WuxinOrder.土.value ==9){
            System.out.println("返回枚举常量的指定值");
        }
    }
}

class Weekday {
    public static final int SUN = 0;
    public static final int MON = 1;
    public static final int TUE = 2;
    public static final int WED = 3;
    public static final int THU = 4;
    public static final int FRI = 5;
    public static final int SAT = 6;
}

enum Wuxin {
    金, 木, 水, 火, 土;
}

enum WuxinOrder {
    // 3.以下在 JVM 中实际是 new 了五个 WuxinOrder 类型的  public static final 实例。调用了构造函数
    金(1), 木(3), 水(5), 火(7), 土(9);

    // 1.为实例创建一个 final 字段 value
    public final int value;

    // 2.在构造函数中赋值
    WuxinOrder(int v){
        this.value = v;
    }
}


