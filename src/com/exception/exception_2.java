package com.exception;

// 抛出异常
// 不仅可以在调用库函数时，处理预设的异常。也可以自定义 throws 异常
// throw 就是最后的语句，再之后的语句不会再执行.若需要在 throw 一个异常后继续执行某些代码，要放在 finally 里

public class exception_2 {
    public static void main(String[] args) {
//        // 1. printStackTrace() 可以方便地打印异常调用链
//        try {
//            process1();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        //2. 自定义抛出异常(另外：上面的try..catch 抛出异常后下面的普通语句继续执行)
//        process3(5);
//
//        //3. 抛出异常时，在调用链中转换异常类型
//        process4("");

        //4. 既然可以在任意普通代码块、try{}、catch{} 中 throw 一个异常。那么在 catch{} 中抛出异常后，finally{} 还会执行吗？
//        process6();

        //5.异常屏蔽：由 process6() 可知先执行 finally 再抛出 catch 的异常。那如果 finally 也抛出异常, catch 的异常将如何处理？
        process7();
    }



    static void process1() {
        process2();
    }

    static void process2() {
        Integer.parseInt(null); // 会抛出 NumberFormatException
    }

    static void process3(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(" <0 is err");
        } else if (n > 10) {
            throw new IndexOutOfBoundsException(" >10 is err");
        }
        System.out.println("fine");
    }

    static void process4(String s) {
        try {
            System.out.println("try");
            process5(s);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("catch");
            // catch 捕捉到 Index 异常 ，但却向上返回了一个 Argument 异常
            // 为了能追踪到完整的异常栈，在构造异常的时候，把原始的 Exception 实例 e 传进去，
            // 新的 Exception 就可以持有原始 Exception 信息。
            throw new IllegalArgumentException(e);// 原始异常 e 一定留住
        }finally {
            System.out.println("finally");
        }
    }

    static void process5(String s) {
        throw new IndexOutOfBoundsException("inner error"); //内层返回的是 Index 异常
    }

    // 在 catch{} 抛出异常
    static void process6() {
        try {
            System.out.println("p7 try:在 catch{} 抛出异常");
            Integer.parseInt("abc"); // 异常情况
        } catch (Exception e) {
            System.out.println("p7 catch:在 catch{} 抛出异常");
            throw new RuntimeException(e);
        } finally {
            System.out.println("p7 finally:在 catch{} 抛出异常");
        }
// 执行先后次序为：try -> catch 正常语句 -> finally -> 抛出的异常对象
//        out:
//        p7 try:在 catch{} 抛出异常
//        p7 catch:在 catch{} 抛出异常
//        p7 finally:在 catch{} 抛出异常
//        Exception in thread "main" java.lang.RuntimeException: java.lang.NumberFormatException: For input string: "abc"
//        at com.exception.exception_2.process7(exception_2.java:85)
//        at com.exception.exception_2.main(exception_2.java:25)
//        Caused by: java.lang.NumberFormatException: For input string: "abc"
//        at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
//        at java.lang.Integer.parseInt(Integer.java:580)
//        at java.lang.Integer.parseInt(Integer.java:615)
//        at com.exception.exception_2.process7(exception_2.java:82)
//	... 1 more
    }

    // catch{} 和 finally{} 都抛出异常（工程情况下，finally 不该抛出异常）
    private static void process7() {
        try {
            Integer.parseInt("abc"); // 会抛出异常
        } catch (Exception e) {
            System.out.println("catch");
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
            throw new IndexOutOfBoundsException();
        }

//        out:
//        catch
//        finally
//        Exception in thread "main" java.lang.IndexOutOfBoundsException
//        at com.exception.exception_2.process7(exception_2.java:104)
//        at com.exception.exception_2.main(exception_2.java:27)

//       可以看到 finally 抛出异常后，原来在catch中准备抛出的异常就“消失”了，
//       因为只能抛出一个异常。没有被抛出的异常称为“被屏蔽”的异常
    }
}
