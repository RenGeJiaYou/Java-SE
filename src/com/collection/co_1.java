package com.collection;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Java 的 Collection 分为三种大类：List、Set、Map
// 从 List 开始
public class co_1 {
    public static void main(String[] args) {
        // 一、线性表 ArrayList
        //      其内部采用 数组 实现。在添加元素时若空间不够，会自动开辟一个更大的新数组并拷贝旧数组过去

        // 1.一些 API
        ArrayList<Integer> s = new ArrayList<>();
        s.add(123);
        s.add(456);
        s.add(null);
        s.remove(0);
        System.out.println(s.size());
        Integer res = s.get(0);

        // 2.ArrayList 的遍历问题
        // 使用 Iterator 会有最好的运行效率，不推荐使用 for 循环的方式
        ArrayList<String> s2 = new ArrayList<>();
        s2.add("a");
        s2.add("b");
        s2.add("c");

        for (Iterator<String> it = s2.iterator(); it.hasNext();){
            String str = it.next();
            System.out.println(str);
        }

        // 简写为 for each 形式，如下
        for (String str : s2) {
            System.out.println(str);
        }
        // 任何实现了 Iterable 接口的集合类均可使用 for each 形式来遍历元素

        // 3. Array 和 List 互换(Array 是 Java 基础语法，而 List 是基于 Array 实现的一组集合类)
        //  List -> Array
        //      (1)使用 toArray() 方法，但会丢失类型信息，不推荐
        Object[] array = s2.toArray();  // 直接返回一个 Array

        //      (2)给 toArray(T[]) 传入一个类型相同的 Array 。如下，元素将被自动复制到传入的 new String[3] 中
        String[] strs = s2.toArray(new String[3]);
        //      上一句的疑点是，用户怎么知道传多大的 String[?] .
        //      实际上，如果传入的数组不够用，List内部会创建一个新的刚好够大的数组，填充后返回；
        //      传入的数组太大，List 把剩下的位置补 null

        //      (3) 常用做法：String[?]传入 List 的长度
        String[] strs2 = s2.toArray(s2.toArray(new String[s2.size()]));

        //  Array -> List
        Integer[] ints ={1,2,3};
        List<Integer>integerList = Arrays.asList(ints);


        //
    }
}
