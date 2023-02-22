package com.io;

import java.io.*;

// Reader / Writer
public class io_6 {
    public static void main(String[] args) throws IOException {
        // 一、Reader
        // Reader 和 InputStream 一样都是输入流。但是 InputStream 读取的是 byte;而 Reader 读取的是 char 流。
        // Reader 暗喻输入的是文本数据
        //1. read() 读取指定的外部文件，每次调用返回下一个 char 代表的数字（0 ~ 65536）
        try (Reader r = new FileReader("novel.txt")) {
            while (true) {
                int n = r.read();
                if (n == -1) break;
                System.out.print((char) n);
            }
        }
        // 2. read() 也可以一次性读入若干 char[] ,此时返回的 int 表示实际读入的 char 的个数
        char[] buffers = new char[1000];
        try (Reader rs = new FileReader("novel.txt")) {
            int n;
            while (true) {
                if ((n = rs.read(buffers)) != -1) {    // 每次都把访问文件的数据尽可能多地塞进 buffer，
                    System.out.println("==========read " + n + " chars.===============");
                } else {
                    break;
                }
            }

        }
        // 3. CharArrayReader
        // 把一个 char[] 数组（像对待一个外部文件那样）作为一个数据源
        Reader charReader = new CharArrayReader("Hello".toCharArray());
        // StringReader 可以直接把一个 String 对象（像对待一个外部文件那样）作为一个数据源
        Reader stringReader = new StringReader("Hello");


        // 4. 使用 InputStreamReader 将任何 InputStream -> Reader
        // 普通的 Reader 实际上是基于 InputStream 构造的
        // 因为 Reader 需要从 InputStream 中读入字节流（byte），再转换为 char 就可以实现字符流。
        // Reader 本质是一个基于 InputStream 的 byte -> char 的转换器，因此可以将任何 InputStream 转换为 Reader
        InputStream inp = new FileInputStream("novel.txt");
        Reader inputStreamReader = new InputStreamReader(inp,"UTF-8"); // 指定底层的 InputStream 和字符集


        // 二、Writer
        // Reader 将外存的 byte 读入为内存的 char,对应地
        // Writer 将内存的 char 写出为外存的 byte.
        //
        // 主要 API:
        // - void write(int c)          写出一个字符（0~65535）
        // - void write(char[] c)       写出一个 char[]
        // - void write(String s)       写出一个 String
        //
        // 1. FileWriter
        try (Writer w1 = new FileWriter("novel.txt")) {
            w1.write(12353);
            w1.write("acd".toCharArray());
            w1.write("acd");
        }
        //2. charArrayWriter 构造一个缓冲区，写入 char ,最后得到一个完整的 char[]
        try (CharArrayWriter w2 = new CharArrayWriter()) {
            w2.write(65);
            w2.write(66);
            w2.write(67);

            System.out.println(w2.toCharArray());
        }
//        StringWriter
//        StringWriter也是一个基于内存的Writer，它和CharArrayWriter类似。实际上，StringWriter在内部维护了一个StringBuffer，并对外提供了Writer接口。

        // OutputStreamWriter
        // 将 OutputStream 转换为 Writer ，接收 char，然后在内部自动转换成一个或多个 byte ，并写入 OutputStream

    }
}
