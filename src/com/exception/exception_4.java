package com.exception;

// NullPointerException 是程序本身的逻辑错误，因此应当尽快暴露出来并修复。不可用 catch 隐藏
public class exception_4 {
    public static void main(String[] args) {
        String s = null;

        System.out.println(s.toLowerCase());

        // 在 Go 中，异常情况会返回 nil ;Java 中不能直接返回 null. 可用 Optional.empty(); 调用方用 Optional.isPersent() 判断

    }
}
