package com.collection;

// Iterator
// Java 的每一种集合都能遍历操作
// 为了让各种各样的集合类都能完成遍历操作，统一使用 Iterator 来实现

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 1.集合类实现 Iterable 接口，该接口要求返回一个实现了 Iterator 接口的对象，这个对象可以是一个 内部类的实例；
// 2.用 Iterator 对象迭代集合内部数据：重载 hasNext() 和 next()。
public class co_7 {
    public static void main(String[] args) {
        // 举例，一个倒序遍历的集合
        ReverseList<String> rlist = new ReverseList<>();
        rlist.add("Apple");
        rlist.add("Orange");
        rlist.add("Pear");
        for (String s:rlist) {
            System.out.println(s);
        }
    }
}

class ReverseList<T> implements Iterable<T> {
    private List<T> list = new ArrayList<>();

    public void add(T t) {
        list.add(t);
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator(list.size());
    }

    // 要有一个实现 Iterator 的内部类
    class ReverseIterator implements Iterator<T> {
        int index;

        public ReverseIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public T next() {
            index--;
            return ReverseList.this.list.get(index);
        }
    }
}