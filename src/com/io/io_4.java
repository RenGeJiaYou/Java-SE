package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

// 操作 zip
public class io_4 {
    public static void main(String[] args) throws IOException {
        // 读取 zip,注意装饰器模式
        try (ZipInputStream input = new ZipInputStream(new FileInputStream("abc.zip"))) {
            ZipEntry zip = null;
            while ((zip = input.getNextEntry()) != null) {
                String name = zip.getName();
                String comment = zip.getComment();
                if (!zip.isDirectory()) {
                    int n;
                    while ((n = input.read()) != -1) {
                        // ...
                    }
                }
            }
        }

        // 写出 zip
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("abc.zip"));
        File[] fs = null;
        for (File f:fs){
            zipOutputStream.putNextEntry(new ZipEntry(f.getName()));
            zipOutputStream.write(Files.readAllBytes(f.toPath()));
            zipOutputStream.closeEntry();
        }
    }
}
