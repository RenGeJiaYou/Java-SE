package com.io;

import java.io.*;

// InputStream / OutputStream
public class io_2 {
    // InputStream 不是接口，而是抽象类。
    // InputStream 是所有输入流的超类
    // InputStream 最重要的方法是 read()
    public static void main(String[] args) throws IOException {
        // 一、FileInputStream
        // try(resource) 语法可以在资源读写异常时自动关闭资源
        try (InputStream input = new FileInputStream("novel.txt")) {
            while (true) {
                int n = input.read();   // read() 读取输入流的下一个字节，并返回字节表示的int值（0~255）. -1 表示不再读取
                if (n == -1) {
                    break;
                }
                System.out.println(n);
//                input.close(); // 编译器在此自动为我们写入finally并调用close()，编译器能这么做是因为 InputStream 实现了 ava.lang.AutoCloseable 接口
            }
        }


        // 二、缓冲
        // 上面的 while 中，每次在外存读取一个字节，效率不高。
        // 不如一次性将若干字节读到内存的缓冲区，后续再慢慢处理。
        byte[] b = new byte[10];
        int n = 0;
        try (FileInputStream i2 = new FileInputStream("novel.txt")) {
            while (n != -1) {
                n = i2.read(b); // 这个 read() 重载方法将外存读到的若干个字节尽可能多地放到定义的 byte[],并返回字节长度。返回 -1 表示没有更多数据
                System.out.println("read " + n + " bytes");
            }
//            read 10 bytes
//            read 5 bytes
//            read -1 bytes
        }


        // 三、阻塞
        // IO 语句必须在返回结果后才能继续执行，但是 IO 多久才能返回结果？这是不确定的。


        // 四、OutputStream
        // 和 InputStream 类似，最重要的方法是 void write(int b) ,其中参数 int b （32 bit）是作为 1 个字节看待的，只取最低 8 位，即 b & 0xff
        // OutputStream 也需要 close() 释放资源
        // flush() 将内存的缓冲数据真正写到外存。当缓冲区满或者调用 close() 时会自动 flush() ,但有时也需要手动管理。
        try (OutputStream output = new FileOutputStream("novel.txt")) {

            // 注意，write() 是覆写  而非追加
            output.write(72); // H
            output.write(101); // e
            output.write(108); // l
            output.write(108); // l
            output.write(111); // o
            output.write("\n".getBytes("UTF-8"));

            // 重载方法 void write(byte[])
            output.write("和 InputStream 类似，最重要的方法是 void write(int b) ,其中参数 int b （32 bit）是作为 1 个字节看待的，只取最低 8 位，即 b & 0xff".getBytes("UTF-8"));

            // output.close();
        }

    }


}

