package com.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class practice_2 {
    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        list.add(new People("Xiao", "Ming", 18));
        list.add(new People("Xiao", "Hong", 25));
        list.add(new People("Bob", "Smith", 20));

        boolean exist = list.contains(new People("Bob", "Smith", 20));
        System.out.println(exist ? "测试成功!" : "测试失败!");
    }
}

class People {
    String firstName;
    String lastName;
    int age;

    public People(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof People) {
            People p = (People) o;

            return Objects.equals(this.firstName, p.firstName) &&
                    Objects.equals(this.lastName, p.lastName) &&
                    this.age == p.age;
        }
        return false;
    }
}

