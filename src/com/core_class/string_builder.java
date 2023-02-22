package com.core_class;

public class string_builder {
    public static void main(String[] args) {
        // JVM 可以直接用 + 号拼接字符串
        String s = "";
        for (int i = 0; i < 100; i++) {
            s = s + "," + i; // 开辟新内存空间存放新字符串，并抛弃旧字符串
        }
        System.out.println(s);
        // 但每次循环，都是返回了一个新字符串，上一趟循环刚新建的字符串马上就作为一个临时变量被抛弃。显然，不仅浪费内存，而且影响 GC。
        // 为了改善这种浪费，Java 提供 StringBuilder 对象，它是**可变**的，可以预分配内存空间（瞬间回忆起 C++ STL 的 Vector）

        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 100; i++) {
            sb.append(i);
            sb.append(',');
        }
        System.out.println(sb.getClass().getName());
        sb.toString();

        /*
        * 从 StringBuilder 源码中可知：
        * 1. StringBuilder 是可变的字符数组，初始容量如果不给定，则默认为16
        * 2. append()/insert() 为所有数据类型做了 overload ,以便将任何实参转为 string 类型（如line 16）并追加/插入原数组，而不是开辟新数组
        * 3. StringBuilder 在单线程下更快；StringBuffer 在多线程下更安全
        * 4. StringBuilder 对象的方法支持链式调用，如sb.append("123").append("456").append("789")
        * */
    }
}
