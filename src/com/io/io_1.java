package com.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

// File 和 Path 对象
// Java 用 File 对象操作文件和目录
public class io_1 {
    public static void main(String[] args) throws IOException {
        // 一、File 的路径.注意 创建 File 对象本身不涉及 IO 操作
        // 1.绝对路径
        File f1 = new File("E:\\test.txt");
        System.out.println(f1.getAbsolutePath()); // E:\test.txt

        //2.相对路径，以用户工作目录开始，即 E:\Project\Java\JavaSE_1
        System.out.println("user.dir:  "+System.getProperty("user.dir"));
        File f2 = new File("..\\test2.txt");
        System.out.println("绝对路径："+f2.getAbsolutePath());   // E:\Project\Java\JavaSE_1\..\test2.txt
        System.out.println("规范路径："+f2.getCanonicalPath());  // E:\Project\Java\test2.txt
        // ↑ 规范路径就是把 . 和 .. 转换成绝对路径「标准化后的路径」


        // 二、File 判断是否为 文件/目录
        System.out.println("f1.isFile(): "+f1.isFile());
        System.out.println("f1.isDirectory(): "+f1.isDirectory());
        File f3 = new File("novel.txt");
        System.out.println(f3.isFile());


        // 三、File 判断文件的权限和大小
        // 主要 API
//        boolean canRead()：是否可读；
//        boolean canWrite()：是否可写；
//        boolean canExecute()：是否可执行；
//        long length()：文件字节大小。

        System.out.println(f3.canWrite());
        System.out.println(f3.canRead());
        System.out.println(f3.canExecute());
        System.out.println(f3.length());


        // 四、File 创建&删除文件
        File f4 = new File("newFile.txt");
        f4.createNewFile();
        f4.delete();

        // createTempFile() 临时创建,配合 deleteOnExit() 在 JVM 退出时删除
        // Java 8 找不到这个 API


        // 五、File 创建&删除目录
        File f5 = new File("pdp");
        f5.mkdir();


        // 六、File 遍历目录和文件（等价于 Linux ls 命令）
        File f6 = new File("c:\\Windows");
        File[] fs1 = f6.listFiles();
        printFiles(fs1);

        File[] fs2 = f6.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".exe");
            }
        });
        printFiles(fs2);


        // 七、Path 在处理路径问题上比 File 更容易
        Path p1 = Paths.get("..","JavaSE_1","src","com");
        System.out.println(p1);     // .\project\study

        System.out.println(p1.toAbsolutePath());    // 绝对路径  E:\Project\Java\JavaSE_1\..\JavaSE_1\src\com
        System.out.println(p1.toRealPath());        // 真实路径  E:\Project\Java\JavaSE_1\src\com
        System.out.println(p1.normalize());         // 相对路径  ..\JavaSE_1\src\com
        File file = p1.toFile();                    // 转换为 File 对象
        System.out.println("file: "+file);
    }

    static void printFiles(File[] fs){
        System.out.println("==========================");
        if (fs!=null){
            for (File f:fs){
                System.out.println(f);
            }
        }
        System.out.println("==========================");
    }
}
