package com.anno;

public class anno_2 {
    // 一、定义注解：
    //      见  Report
    // 二、元注解( meta-annotation )：可以修饰其它注解的注解，来自 Java 标准库，不需要自己编写
    // 1. @Target(必写)
    //      定义了 Annotation 应当应用于源码的哪些位置：
    //          类或接口：ElementType.TYPE；
    //          字段：ElementType.FIELD；
    //          方法：ElementType.METHOD；
    //          构造方法：ElementType.CONSTRUCTOR；
    //          方法参数：ElementType.PARAMETER。

    // 2. @Retention(必写)
    //      定义了 Annotation 的生命周期
    //          编译期
    //          仅 .class 文件（默认值）
    //          运行期（最常用）

    // 3. @Repeatable(一般不必写)
    //      定义了 Annotation 是否可以重复

    // 4. @Inherited(一般不必写)
    //      定义了 Annotation 能否从父类继承

}

@Report(name = "a", tags = {"1","2","3"})
@Report(name = "b",tags = {"4","5","6"})
class Hello2 {

}

class World extends Hello2{
    // 由于注解 @Report 使用了元注解 @Inherited ,子类 World 在继承父类 Hello2 时同时也会继承注解 @Report
}

