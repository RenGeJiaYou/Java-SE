package com.io;

import java.io.*;

// Filter 模式 / 装饰器模式：Decorator
public class io_3 {
    // 装饰器的设计模式流程如下：
    // A a;
    // B b = new B(a);
    // C c = new C(b)
    // 后面不管是哪个类的实例，都可以用一开始 A 类型的引用变量接收

    // 运用装饰器模式，编写一个 CountInputStream ，它的作用是对输入的字节进行计数
    public static void main(String[] args) throws IOException {
        byte[] data = "hello, world!".getBytes("UTF-8");
        // Java 果真又臭又长
        // CountInputStream 拓展了一些方法，通过装饰器模式让 ByteArrayInputStream 类实例使用这些方法                 `
        try (CountInputStream cis = new CountInputStream(new ByteArrayInputStream(data))) {
            int n;
            while((n = cis.read())!=-1){
                System.out.println((char) n);
            }
            System.out.println("Total read " + cis.getBytesRead() + " bytes");
        }
    }




}


class CountInputStream extends FilterInputStream {
    private int count = 0;

    protected CountInputStream(InputStream in) {
        super(in);
    }

    public int getBytesRead() {
        return this.count;
    }

    public int read() throws IOException {
        int n = in.read();
        if (n != -1) {
            this.count++;
        }
        return n;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        if (n != -1) {
            this.count += n;
        }
        return n;
    }
}
