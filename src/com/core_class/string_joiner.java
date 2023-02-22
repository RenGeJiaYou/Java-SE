package com.core.str;

import java.util.StringJoiner;

public class string_joiner {
    public static void main(String[] args) {

        String[] names = {"Bob", "Alice", "Grace"};

        // 原需求为：
        StringBuilder sb = new StringBuilder();
        sb.append("Hello ");// 简化
        for (String name : names) {
            sb.append(name).append(',');
        }
        // 删除最后的','
        sb.delete(sb.length() - 1, sb.length()); // 简化
        sb.append("!");// 简化
        System.out.println(sb.toString());

        // 一、StringJoiner 对象简化拼接操作
        // ===可以看到，需要考虑删除操作，处理流程还是有些繁琐。Java 提供了一个对象 StringJoiner 通过指定分隔符拼接对象====
        StringJoiner sj = new StringJoiner(",", "Hello ", "!");// 指定分隔符,前缀，后缀
        for (String name : names) {
            sj.add(name); // 值传递
        }
        System.out.println(sj.toString());

        // 二、String.join() 在不需要前后缀的情况下进一步简化
        String s = String.join(",",names);
        System.out.println(s);

        /*
        * 1.join()是 String 的静态方法
        * 2.join() 内部调用了 StringJoiner 对象
        * */

        // 三、测试
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String select = buildSelectSql(table, fields);
        System.out.println(select);
        System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");

    }

    static String buildSelectSql(String table, String[] fields){
        StringJoiner sj = new StringJoiner(", ","SELECT "," FROM "+table);
        for (String f:fields) {
            sj.add(f);
        }
        return sj.toString();
    }
}
