package com.anno;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})        // 要求 Report 注解能应用于 类 / 方法 / 字段
@Retention(RetentionPolicy.RUNTIME)                                     // 通常自定义的注解都是 RUNTIME
@Inherited                                                              // 定义子类可继承父类定义的Annotation
@interface Reports {
    Report[] value();
}


// 定义的语法为 @interface
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Reports.class)                                              // 修饰的注解可以重复使用。但 @Repeatable 修饰的注释和元注释 @ Target 冲突
@interface Report {
//      注解中只能定义成员变量，且以「无参函数」的形式定义
    int type() default 0;
    String level() default "info";
    String name() default  "default";      // 最常用的参数定义为 value()，传入时就可省略形参名
    String[] tags();                  // 数组的形式，传参时直接传 {}
}

