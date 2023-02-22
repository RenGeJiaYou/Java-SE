package com.collection;

import java.util.*;

// Collections 提供了一堆方便的静态方法
public class co_8 {
    public static void main(String[] args) {
        //1. 创建空集合。注意这种方式创建的空集合不能添加/删除元素
        List<String> list = Collections.emptyList();
        Map<Integer, String> map = Collections.emptyMap();
        Set<Integer> set = Collections.emptySet();
//        list.add("sad");  // 将报错，


        //2. 创建单元素集合.也是不可变的集合
        List<String> list1 = Collections.singletonList("dad");
        Map<Integer, String> map1 = Collections.singletonMap(1,"22");
        Set<Integer> set1 = Collections.singleton(1);


        //3. 排序/洗牌。注意需传入可变 List
        List<String> list2 = new ArrayList<>();
        list2.add("apple");
        list2.add("pear");
        list2.add("orange");
        System.out.println(list2);
        // 排序:
        Collections.sort(list2);    // 对原有 List 进行排序
        // 排序后：
        System.out.println(list2);
        // 洗牌
        Collections.shuffle(list2); // 随机打乱 List 内部元素的顺序
        // 洗牌后：
        System.out.println(list2);

        //5. 不可变集合
        List<String> list3 = Collections.unmodifiableList(list2);
        Map<Integer, String> map3 = Collections.unmodifiableMap(new HashMap<>());
        Set<Integer> set3 = Collections.unmodifiableSet(new TreeSet<>());


    }
}
