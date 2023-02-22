package com.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

// 序列化： 一个 Java Object -> byte[],以便网络传输
// 一个能够序列化的对象，必须实现 java.io.Serializable ，这是一个未定义任何方法的“标记接口”
public class io_5 {
    public static void main(String[] args) throws IOException {
        // 一、序列化
        // 把一个 Java 对象变为 byte[] 数组，需要使用 ObjectOutputStream。它负责把一个 Java 对象写入一个字节流：
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        // 把待写出的数据放到 内存创建的 buffer 对象中
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            // 写入 int
            output.writeInt(124);
            // 写入 String
            output.writeUTF("会议论文");
            // 写入 Object
            output.writeObject(new Double(12.43));

            System.out.println(Arrays.toString(buffer.toByteArray()));
        }


        // 二、反序列化
        // ObjectInputStream 从缓冲流中读取 Java 对象
//        try (ObjectInputStream input = new ObjectInputStream()) {
//            int n = input.readInt();
//            String s = input.readUTF();
//            Double d = input.readDouble();
//            Double d2 = (Double) input.readObject();// 注意, readObject() 返回的 Object 需要转型
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }


        // 三、可能遇到的异常
        // 3-1 ClassNotFoundException
        // 场景：电脑 A 把 自定义类如 Person 类对象 通过网络 传给电脑 B ，而 B 并未定义这个类，无法反序列化，就会报 ClassNotFoundException
        //
        // 3-2 InvalidClassException
        // 场景：序列化的 Person 对象有一个 int 字段，反序列化时该字段被改为 long 。
        // 为了解决这种情况，Java 为类提供了一个静态常量字段 serialVersionUID ，自定义类也可由 IDE 生成该字段,增加或修改了字段，可以改变 serialVersionUID 的值


        // 安全性
        // 因为Java的序列化机制可以导致一个实例能直接从byte[]数组创建，而不经过构造方法，因此，它存在一定的安全隐患。一个精心构造的byte[]数组被反序列化后可以执行特定的Java代码，从而导致严重的安全漏洞。
        // 实际上，Java本身提供的基于对象的序列化和反序列化机制既存在安全性问题，也存在兼容性问题。
        // 更好的序列化方法是通过JSON这样的通用数据结构来实现，只输出基本类型（包括String）的内容，而不存储任何与代码相关的信息。
    }
}
