package com.genic;

// 泛型 & 多态
// 泛型在类和接口中使用
public class gen_2 {
    public static void main(String[] args) {
        //3. 实际调用时，传入具体类型
        A<String,Integer> a = new A<>();
        a.test();

    }

// 2. 类实现接口时，也不指定具体类型，仍然传递泛型
    static class A<T,E> implements Test<T,E>{

        @Override
        public T test() {
            return null;
        }
    }
}

// 1. 接口定义时使用泛型
interface Test<T,E>{
    T test();
}