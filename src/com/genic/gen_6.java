package com.genic;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// 函数式接口
// 是 JAVA 8 提供的一系列 lambda 表达式接口，共同特征是
//      1.都有 @FunctionalInterface 注解。
//      2.都只有一个方法要实现
// 有 4 个主要的接口
public class gen_6 {
    public static final Consumer<Student> STUDENT_CONSUMER = student -> System.out.println(student + " is consumed");
    public static final Function<Integer,String>INTEGER_STRING_FUNCTION = Objects::toString;    //将对象转为字符串
    public static final Predicate<Student>STUDENT_PREDICATE = student -> student.toString() == "";  // 判断后返回 bool 结果，lambda 已经实现接口内部 test()

    public static void main(String[] args) {
        //一、Supplier
        // 举例，一个专门供给 Student 对象使用的 Supplier
        Supplier<Student> studentSupplier1 = new Supplier<Student>() {
            @Override
            public Student get() {
                return new Student();
            }
        };
        // 以上完全等价于
        Supplier<Student> studentSupplier2 = () -> new Student();
        // 或
        Supplier<Student> studentSupplier3 = Student::new;


        studentSupplier1.get().hello();
        studentSupplier2.get().hello();
        studentSupplier3.get().hello();


        // 二、Consumer
        // 已经在 main() 外面定义了一个针对 Student 类型的接口，并以 lambda 风格实现了唯一的一个 accept() 函数（Consumer 接口只有这一个函数，实现时无需具名）
        // Consumer 中已定义好的 andThen() 是在 accept(stu) 结束后继续对 stu 进行某些操作（联系 JS 中的 Promise）
        Student s = new Student();
        STUDENT_CONSUMER.andThen(st->{
            System.out.println(st+" and then");
        }).accept(s);


        // 三、Function
        // 消费一个对象，然后向外供给一个对象
        String str = INTEGER_STRING_FUNCTION.apply(13);
        System.out.println(str+str.getClass());
        // compose() 是在 apply() 前执行.compose() 的结果是 apply() 的入参
        // 实现 "aaabbbccc" -> 9 -> "9"
        String str2 = INTEGER_STRING_FUNCTION.compose(String::length).apply("aaabbbccc");
        System.out.println(str2);


        // 四、Predicate
        // 接收一个参数，判断后返回 Boolean 结果
        System.out.println(STUDENT_PREDICATE.test(s));
    }


    public static class Student {
        public void hello() {
            System.out.println("我是学生！");
        }
    }
}


