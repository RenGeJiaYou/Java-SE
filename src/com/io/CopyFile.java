package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        // 0.参数校验
        if (args.length != 2) {
            System.err.println("Usage:\\n  java CopyFile.java <source> <target>");
            System.exit(1);
        }
        // 1.把文本内容读入内存
        String src = args[0];   // srcPath
        String dis = args[1];   // disPath

        // 安全检查
        File disFile = new File(dis);
        if (!disFile.isFile())disFile.createNewFile();

        byte[] b = new byte[1024];
        int n = 0;
        StringBuilder sb = new StringBuilder();
        String s = "";            // 读取的内容存入此处
        try (FileInputStream input = new FileInputStream(src)) {
            while ((n = input.read(b)) != -1) {
                sb.append(new String(b, "UTF-8"));   // 难点在于 append() 时类型的转换，采用 String 的含 charsetName 的重载构造参数将 byte[] 转为 UTF-8 编码的 String
            }
            s = sb.toString().trim();
//            System.out.println(s);
        }

        //2. 把内存写出副本
        try (FileOutputStream output = new FileOutputStream(dis)) {
            output.write(s.getBytes("UTF-8"));
        }

    }
}
