package com.anno;
// 处理注解
// 注解实际上也是一种类,均继承自 java.lang.annotation.Annotation
// 因此若想读取注解，应当用反射

import java.lang.reflect.Method;

@Report(name = "Report",tags = {"a","b"})
public class anno_3 {
    public static void main(String[] args) throws NoSuchMethodException {
        // 获取修饰指定 类 的指定注解
        Report r = anno_3.class.getAnnotation(Report.class);
        System.out.println(r.name());

        // 获取修饰指定 方法 的指定注解
        Method test = anno_3.class.getMethod("test");
        Report testAnnotation = test.getAnnotation(Report.class);
        System.out.println(testAnnotation.name());

    }

    @Report(name = "method",tags = {"asd"})
    public void test(){

    }
}
