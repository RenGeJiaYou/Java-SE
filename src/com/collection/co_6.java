package com.collection;

import java.util.*;

// Queue
public class co_6 {
    public static void main(String[] args) {
        //1. 主要 API :添加、删除、查找。同时又分抛出异常和返回 false 两类。
        Queue<String> qs = new PriorityQueue<>();
//                          throw Exception	    返回false或null
//        添加元素到队尾	    add(E e)	        boolean offer(E e)
//        取队首元素并删除	    E remove()	        E poll()
//        取队首元素但不删除	E element()	        E peek()


        try {
            qs.add("Apple");
            System.out.println("添加成功");
        } catch (IllegalStateException e) {
            System.out.println("添加失败");
        }
        System.out.println(qs.offer("Banana"));

        try {
            System.out.println(qs.remove());
        } catch (Exception e) {
            System.out.println("删除失败");
        }
        System.out.println(qs.poll());

        try {
            System.out.println(qs.element());
        } catch (Exception e) {
            System.out.println("查找失败");

        }
        System.out.println(qs.peek());


        // 2. LinkedList 既实现了 List 接口，又实现了 Queue 接口(这意味着同时实现了两个接口声明的全部方法)。
        // 因此，LinkedList 用一个 List 接收时，可调用 List 的 API；用 Queue 接收时，可调用 Queue 的 API
        // 这是一个List:
        List<String> list = new LinkedList<>();
        // 这是一个Queue:
        Queue<String> queue = new LinkedList<>();


        // 3. PriorityQueue 出队顺序和「优先级」有关，而不是入队顺序。
        // 优先级取决于 传入元素类型实现的 Comparable 接口

        // 添加3个 String 到队列,String 已经实现 Comparable 接口:
        qs.offer("apple");
        qs.offer("pear");
        qs.offer("banana");
        System.out.println(qs.poll()); // apple
        System.out.println(qs.poll()); // banana
        System.out.println(qs.poll()); // pear
        System.out.println(qs.poll()); // null,因为队列为空

        // 添加自定义类型到优先队列(业务逻辑为：让V开头的客户比其它客户优先出队)
        Queue<User> users = new PriorityQueue<>();
        users.offer(new User("Ace", "A123"));
        users.offer(new User("Bob", "V7"));
        users.offer(new User("Candy", "A45"));

        System.out.println(users.poll());
        System.out.println(users.poll());
        System.out.println(users.poll());


        // 4. 双端队列 Deque
        Deque<Integer> dq = new ArrayDeque<>();
        Deque<Integer> dq2 = new LinkedList<>();
        dq.addLast(1);
        dq.addFirst(2);


        // 5. stack 常用 API :push() / pop() / peek()
        // Java 没有直接的 Stack 接口或类，只能用 Deque 模拟
        Deque<Integer>stack = new LinkedList<>();
        stack.push(1);
        System.out.println(stack.pop());
        stack.peek();
    }


}


// 要想满足 PriorityQueue 的排序要求，实现 Comparable 接口。有两种形式：
// 1. 直接让该类实现 Comparable 接口，并 override compareTo()
// 2. 另外声明一个类，实现 Comparator<User> 并override compare(User u1, User u2)。然后在声明一个优先队列实例时传入该类
class User implements Comparable {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String toString() {
        return name + "/" + number;
    }


    @Override
    public int compareTo(Object o) {
        User u = (User) o;
        if (number.charAt(0) == u.number.charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头,比较号的大小:
            return number.compareTo(u.number);
        }
        if (number.charAt(0) == 'V') {
            // u1的号码是V开头,优先级高:
            return -1;
        } else {
            return 1;
        }

    }
}
