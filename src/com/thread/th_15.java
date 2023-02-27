package com.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Concurrent 集合 针对常用的集合都做了线程安全的版本
//        List	ArrayList	                CopyOnWriteArrayList
//        Map	HashMap	                    ConcurrentHashMap
//        Set	HashSet / TreeSet	        CopyOnWriteArraySet
//        Queue	ArrayDeque / LinkedList	    ArrayBlockingQueue / LinkedBlockingQueue
//        Deque	ArrayDeque / LinkedList	    LinkedBlockingDeque
//
// 使用方法完全相同
public class th_15 {
    public static void main(String[] args) {
        Map<String,Integer> map = new ConcurrentHashMap<>();

        // 假设以下代码是在不同线程中读写
        map.put("A",1);

        map.put("B",2);

        map.put("C",3);
    }
}
