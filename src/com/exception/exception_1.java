package com.exception;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

// 捕获异常
// 异常是一种 class.继承自 Throwable 类。Throwable 的继承有两个方向：
// 1. Error: 较为严重。系统对此无能为力。包括内存耗尽、栈溢出。Java 规定不需要捕获。
// 2. Exception: 较为轻微。可以被捕获并处理。Java 规定一定要捕获。但不包括 RuntimeException 及其子类
//    Exception 又可以分为两大类：
//    2-1 RuntimeException 及其子类。无需捕获
//    2-2 非 RuntimeException 及其子类，包括 IOException、ReflectiveOperationException 等。必须捕获

// 异常处理的代码包括的关键词有 try、catch、finally、throw
// throw: 如果一个被调函数签名处写了 throws XxxException ,那就意味着主调函数一定要 catch 这个 Exception ;如果主调函数不这样做，就要自己的签名也加一个 throws 语句.
// 直到 main 函数签名上也写了 throws .如果你真的在 main() 才 throw Exception, 那么该 Java 程序遇到任何异常将直接退出
// 最终，一个 throws 必须有 try...catch

public class exception_1 {
    public static void main(String[] args) {
        // 1.捕获异常，使用 try catch
        byte[] bs = toGBK("中文");

        System.out.println(Arrays.toString(bs));

        // 2.多捕获异常
        func2();

        //3.finally 语句
        func3();

        //4.多捕获异常的简写
        func4();

    }


    private static byte[] toGBK(String s) {
        try {
            return s.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);// 打印异常信息
            return s.getBytes();
        }
    }

    private static void func2() {
        // 1. 多个 catch 会被依次匹配，但只有一个最终命中
        // catch 的排序问题：如果两个 catch 捕获的异常类有继承关系，捕获子类异常的 catch 语句在前
        // 这是因为，如果父类异常的 catch 语句在前，当 throw 出一个子类异常时，程序会按序匹配到前面的父类 catch。而后面真正的子类 catch 永远不会民众
        try {
            process1();
            process2();
            process3();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        } catch (IOException  eq){
            System.out.println(eq);
        } catch (Exception ee){
            System.out.println(ee);
        }
    }

    private static  void func3(){
        try {
            process1();
            process2();
            process3();
        } catch (Exception e) {
            System.out.println(e);;
        } finally {
            // finally 语句不是必须的，且总在最后执行.但发生异常时，JVM 将先执行 finally ,后抛出异常
            // case 1 未发生异常：执行完 try{} ,再执行 finally{}
            // case 2 发生了异常：中断 try{}, 执行 catch{},再执行 finally{}
            System.out.println("finally");
        }
    }

    private static void func4() {
        // 可用位或运算符 | 简化多个 catch 语句，条件是
        // 1. 各个异常之间没有继承关系，是相互正交的
        // 2. 各个原 catch 块内的业务一致
        try {
            process1();
            process2();
            process3();
        } catch (NumberFormatException | IOException e) {
            System.out.println(e);
        }  catch (Exception ee){
            System.out.println(ee);
        }
    }

    //
    public static void process1() throws UnsupportedEncodingException {
        System.out.println("p1");
    }

    public static void process2() throws IOException  {
        System.out.println("p2");
    }

    public static void process3() throws Exception {
        System.out.println("p3");
    }

}




