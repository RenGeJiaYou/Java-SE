package com.genic;

import java.util.Arrays;

// 泛型在 方法 中的使用
// <> 位于修饰符的返回值类型前面
public class gen_3 {
    public static void main(String[] args) {
        // 例1
        Integer foo = Foo(3);
        String xyz = Foo("xyz");

        // 例2 泛型在工具类中的应用. 研究 Comparator 的泛型实现，实现降序排序
        Integer[] arr = {1, 4, 5, 2, 6, 3, 0, 7, 9, 8};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr, (o1, o2) -> o2 - o1);// 第一个参数是<T>,将根据实际传入的 arr 确定类型
        Arrays.copyOf(arr, 5);// 第一个参数是<T>,将根据实际传入的 arr 确定类型。
        System.out.println(Arrays.toString(arr));


        // 例3 对 Person 类的几个实例进行排序
        Person[] ps = new Person[]{
                new Person("a", 1),
                new Person("c", 3),
                new Person("b", 2),
                new Person("h", 8),
                new Person("z", 26),
                new Person("k", 11),
                new Person("g", 7)

        };
        Arrays.sort(ps);
        System.out.println(Arrays.toString(ps));
    }

    public static <T> T Foo(T t) {
        System.out.println(t);
        return t;
    }

    static class Person implements Comparable<Person> {
        String name;
        Integer score;

        Person(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String toString() {
            return this.name + "," + this.score;
        }

        @Override
        public int compareTo(Person o) {
            return this.score.compareTo(o.score);       // 基本类型 int 不是类，更没有方法，也不可能实现 compareTo() .因此字段最好都是包装类
        }
    }
}
