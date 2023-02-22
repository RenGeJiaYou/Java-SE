package com.base.innerClass;

import java.util.HashMap;

public class AnonClass2 {
    public static void main(String[] args) {
        HashMap<String,String> m1 = new HashMap<>();
        //对于JDK1.8,以下通过继承普通类来实现的匿名类是不可行的。
//        HashMap<String,String> m2 = new HashMap<>(){};
//        HashMap<String, String> map3 = new HashMap<>() {
//            {
//                put("A", "1");
//                put("B", "2");
//            }
//        };
    }
}
