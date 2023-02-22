package com.anno;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

// 注解是一种特殊的注释，放在 类、方法、字段、参数前。
// 对代码逻辑没有任何影响，但编译器据此执行某些操作。
public class anno_1 {
    // 注解有三类
    // 1.由编译器使用的注解，只在编译期被使用，不会进入编译后的代码
    //      例如 @override 让编译器检查是否正确覆写；@SuppressWarning 要求编译器忽略此处代码的警告
    // 2.由处理 .class 文件的工具使用的注解，将修改到 class ，但不会存在于内存中。一般是一些底层库使用
    // 3.程序运行期能读取的注解，在加载后一直存在于 JVM

}

@Resource(lookup = "hello")
@Report(name = "Hello-report",tags = {"ere"})
class Hello {
    int n;

    @PostConstruct
    public void hello(String name) {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return "Hello";
    }
}
