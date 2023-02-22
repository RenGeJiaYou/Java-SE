package com.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// Files 工具类，受内存所限，只能读取小文件（如配置文件），读取大型文件仍然需要 文件流
public class io_8 {
    public static void main(String[] args) throws IOException {
        //1.把外部文件的全部内容读取为 byte[]
        Path path = Paths.get("novel.txt");
        byte[] bytes = Files.readAllBytes(path);

        //2.把外部文件的全部内容读取为 String
//        String s = Files.readString()                 Java 8 没这个函数
        List<String> ls = Files.readAllLines(path);

        // 3. 写出文件
        byte[] b = {1, 2, 3, 4, 5, 6, 7, 5, 45};
        Files.write(path, b);

    }

}
