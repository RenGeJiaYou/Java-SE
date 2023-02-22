package com.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class co_3 {
    public static void main(String[] args) {
        Student s = new Student("Xiao Ming", 99);
        Map<String, Student> map = new HashMap<>();

        // 1. put() 和 get() 完成读写
        map.put("xm", s);
        Student target = map.get("xm");
        System.out.println(target.score);
        System.out.println(map.containsKey("asda")); // 只是想查询某个 key 是否存在

        // 2.试图放入多个相同 key 的键值对，只保留最新的那个，旧 value 将被 “冲刷” 掉。
        //      注意1：put() 可以在存入一个 key 的新值时会返回该 key 的旧值
        //      注意2：value 是可以重复的
        Student s1 = new Student("A", 132);
        Student s2 = new Student("B", 45);

        map.put("zx", s1);
        Student res = map.put("zx", s2);    // 若已存在该 key 旧值,在存入一个新值时 put() 会同时返回旧值。

        System.out.println(res.score);
        System.out.println(map.get("zx").score);

        // 3. 遍历 map
        // 3-1. keyset()  包含不重复的 key 集合
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key).name + " " + map.get(key).score);
        }
        // 3-2. entrySet() 包含每一个 k-v 对
        for (Map.Entry<String, Student> e : map.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue().name + " " + e.getValue().score);
        }

        // 4. equals() ，对于 key 来说，不管传入几个实例对象，只要实例值相等，得到的 value 应该相同。
        map.get(new String("xm"));  // 正确返回 “zx B 45”，因为 String 已经正确返回

        // 5. hashCode(), Java 之所以能通过 key 哈希出某个 value 的索引，是因为这个 key 的类型实现了 hashCode() ,以返回一个正整数
        // ※一个自定义类要作为 map 的 key ,得正确覆写
        //      equals() ：
        //          相等的两个 key 实例调用 equals() 必须返回true；
        //      和
        //      hashCode()：
        //          如果两个对象相等，则两个对象的 hashCode() 必须相等；
        //          如果两个对象不相等，则两个对象的 hashCode() 尽量不要相等。
        //
        //      编写 equals() 和 hashCode() 遵循的原则是：
        //      equals() 用到的用于比较的每一个字段，都必须在 hashCode() 中用于计算；
        //      equals() 中没有使用到的字段，绝不可放在 hashCode() 中计算。

        // 6. hash 冲突
        // 发生冲突且 key 不同的键值对，在 hashmap 中将以 List<Entry<K, V>> 的形式存储。


    }
}

class Student {
    public String name;
    public int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }

    @Override
    public boolean equals(Object obj) {
        Student s = (Student) obj;
        return Objects.equals(this.name, s.name) && this.score == s.score;
    }
}
