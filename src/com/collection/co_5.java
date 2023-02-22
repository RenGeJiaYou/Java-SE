package com.collection;

import java.util.*;

// 使用 set
// Map 可以存储不重复的键值对，但需要自定义 equals() 和 hashCode()
// 如果不需要 value ,只需要存储 key 部分，可选用 Set 容器
public class co_5 {
    public static void main(String[] args) {
        //1.主要 API
        Set<String> s = new HashSet<>();
        System.out.println(s.add("a"));
        System.out.println(s.add("b"));
        System.out.println(s.remove("a"));
        System.out.println(s.contains("a"));
        System.out.println(s.contains("b"));
        System.out.println(s.size());

        //2. set 常用于去重,HashSet 数据结构上只是对 hashMap 的简单封装
        //       实际上相当于只存储 key、不存储 value 的Map。
        //       同样也要实现 equals() 和 hashCode()
        //       但其本身是无序的,既不是按输入顺序，也不是按字母顺序
        s.add("c");
        s.add("v");
        s.add("r");
        s.add("asd");
        for (String c : s) {
            System.out.println(c);
        }

        //3. 继承自 sortedSet 的 TreeSet
        Set<String> ts = new TreeSet<>();
        ts.add("c");
        ts.add("v");
        ts.add("r");
        ts.add("asd");
        for (String c : ts) {
            System.out.println(c);
        }

        //4.使用 set 对 list 去重
        List<String> ls = new ArrayList<>();
        ls.add("a");
        ls.add("a");
        ls.add("b");
        ls.add("b");

        Set<String> ss = new TreeSet<>(ls);
        for (String c:ss
             ) {
            System.out.println(c);
        }
    }
}
