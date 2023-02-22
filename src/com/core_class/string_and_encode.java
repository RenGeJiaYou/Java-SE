package com.core_class;

import java.util.Arrays;

// 一、字符串和编码
public class string_and_encode {
    public static void main(String[] args) {
        // 1.String
        // String 是一个引用类型，本身也是一个 class.但是 JVM 对其经过特殊优化，能够直接表示为
        String s1 = "Hello";

        // 实际上,字符串的值位于 String类 内部的一个 private final char[] 里，因此实际上是这样的：
        String s2 = new String(new char[]{'H', 'e', 'l', 'l', 'o'});
        // 先 new 一个 String 对象，构造函数内传入 new 一个 char[] 对象，char[] 对象再用 {} 传入字符常量

        // 从 private final char[] 可看出 String 值是不可变的
        String s3 = "Hello";
        System.out.println(System.identityHashCode(s3));  // HashCode 为 460141958
        s3 = s3.toUpperCase();
        System.out.println(System.identityHashCode(s3)); // HashCode 为 1163157884。说明toUpperCase() 并没有修改char[] ,而是对s3 返回了一个新对象

        // 字符串的比较：作为一种引用类型，应当比较值，而非内存地址
        String str = "Hi";
        String str2 = str;   // 指向同一个内存地址
        String str3 = "Hi";  // 同样的值

        System.out.println("str==str2: " + String.valueOf(str == str2));  // true. str 和 str2 指向同一个内存地址，因此==操作符判定为 true
        System.out.println("str==str3: " + String.valueOf(str == str3));  // true. 之所以返回 true ，是因为 Java 在编译器会自动将值相同的字符串当作一个对象放入常量池.这种写法只是凑巧true
        System.out.println("str.equals(str2): " + String.valueOf(str.equals(str2)));// true.
        System.out.println("str.equals(str3): " + String.valueOf(str.equals(str3)));// true.

        // 测试两个字面值相同的 String 是否 ==，并避开 JVM 的「自动合并多个等值字符串为一个常量」机制
        String str4 = "hi";
        String str5 = "HI".toLowerCase();
        System.out.println("str4==str5: " + String.valueOf(str4 == str5)); //false.

        // String 和 char[] 可以相互转换
        char[] a1 = "abcdef".toCharArray();
        String a2 = new String(a1); //注意，传入的 a1 是值传递

        // 从String 的不变性可知，如果传入的对象有可能改变，我们需要复制而不是直接引用。
        int[] scores = new int[]{88, 77, 51, 66};
        Score s = new Score(scores);
        s.printScores(); // [88, 77, 51, 66]
        scores[2] = 99;
        s.printScores(); // [88, 77, 99, 66];构造函数采用Array.copyOf() 改造为值传递


    }
}

class Score {
    private int[] scores;

    public Score(int[] scores) {
//        this.scores = scores; //直接引用了来自外界的变量（地址传递）。外部变量的改变将影响到内部值

        // 转为值传递
        this.scores = Arrays.copyOf(scores,scores.length);
    }

    public void printScores() {
        System.out.println(Arrays.toString(scores));
    }
}
