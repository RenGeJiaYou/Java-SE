package com.collection;

// EnumMap 、 TreeMap 和 Properties

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;

public class co_4 {
    public static void main(String[] args) {
        // 1.EnumMap
        // 如果一个键值对的 key 不是String、int 这种有海量取值范围的（如 int 可以取-2^31——2^31-1）类型
        // 而是一个 enum 类型，只有有限几个取值。可改用 EnumMap 获得更紧凑的数据结构
        Map<DayOfWeek, String> map = new EnumMap<>(DayOfWeek.class); // EnumMap 实例常用 Map 变量来接收
        map.put(DayOfWeek.MONDAY, "星期一");
        map.put(DayOfWeek.TUESDAY, "星期二");
        map.put(DayOfWeek.WEDNESDAY, "星期三");
        map.put(DayOfWeek.THURSDAY, "星期四");
        map.put(DayOfWeek.FRIDAY, "星期五");
        map.put(DayOfWeek.SATURDAY, "星期六");
        map.put(DayOfWeek.SUNDAY, "星期日");

        System.out.println(map);


        //2.TreeMap
        // 有时需要 key 有序排列(大小或字母顺序)的 map ，即 SortedMap. TreeMap 是它的子类
        Map<String, Integer> sm = new TreeMap<>();
        sm.put("orange", 1);
        sm.put("apple", 2);
        sm.put("watermelon", 5);

        for (String k : sm.keySet()) {
            System.out.println(k);
//        output:
//            apple
//            orange
//            watermelon
        }


        //3.要想正确使用 TreeMap ,传入的 key 的类型必须实现了 Comparable 接口
//        Map<China,Integer> sm2 = new TreeMap<>(new Comparator<China>() {
//            @Override
//            public int compare(China o1, China o2) {
//                return o1.name.compareTo(o2.name);
//            }
//        });
        // 以上可简化为lambda,匿名函数形参的类型将默认为 key 的类型：
        Map<China, Integer> sm2 = new TreeMap<>((o1, o2) -> o1.name.compareTo(o2.name));


        sm2.put(new China("Tom"), 1);
        sm2.put(new China("Bob"), 2);
        sm2.put(new China("Lily"), 3);
        for (China key : sm2.keySet()) {
            System.out.println(key);
        }
        // {Person: Bob}, {Person: Lily}, {Person: Tom}
        System.out.println(sm2.get(new China("Bob"))); // 2
        // 注意，TreeMap 不使用 equals() 和 hashCode()


        //4.Comparable 接口的一个稍复杂的实现
        //Comparable 内的匿名方法负责比较传入的两个元素a和b，
        //      如果a<b，则返回负数，通常是-1，
        //      如果a==b，则返回0，
        //      如果a>b，则返回正数，通常是1。
        //默认是升序排序的，返回 -1 。如果希望降序排序，匿名方法应当写为 return p1 > p2 ? -1 : 1
        Map<AStudent, Integer> sm3 = new TreeMap<>((o1, o2) -> {
            if (o1.score == o2.score) {     // 如果在两个值相等时不返回 0 ，将无法使用值相同的新实例查找 TreeMap 中的已有实例
                return 0;
            }
            return o1.score > o2.score ? -1 : 1;
        });
        sm3.put(new AStudent("Tom", 77), 1);
        sm3.put(new AStudent("Bob", 66), 2);
        sm3.put(new AStudent("Lily", 99), 3);
        for (AStudent key : sm3.keySet()) {
            System.out.println(key);
        }
        System.out.println(sm3.get(new AStudent("Bob", 66))); // null?2




    }
}

class China {
    public String name;
    public int score;

    China(String name) {
        this.name = name;
    }

    public String toString() {
        return "{China: " + name + "}";
    }
}

class AStudent {
    public String name;
    public int score;

    AStudent(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return String.format("{%s: score=%d}", name, score);
    }
}
