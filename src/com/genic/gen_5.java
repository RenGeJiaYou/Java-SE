package com.genic;


// 关于类型擦除
// 泛型在编译之后， JVM 看到的只有 Object 类（<T> 被擦除掉了），或者限定的上界类.
// 在调用一个泛型类，传入实际类型时，编译器会自动地、安全地将 Object 对象强制转换类型。
// 因此，泛型只是在编译阶段做一个「语法检查」而已。
public class gen_5 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        A<String> a1 = new A<>(String.class);
        a1.test("as");
        A<Integer> a2 = new A<>(Integer.class);
        a2.test(123);


        // 关于继承了一个具体的泛型类后，重写方法仍然有效的问题
        // 如泛型类 A 中的方法在编译后实际是 Object test(Object t)
        // 类 B 在继承 A<String> 后重写的方法是 String test(String t)
        // B 的方法返回值类型和参数列表均与 A 的不同，为何 override 正常通过？
        //
        // 编译后的 B 依然是 Object test(Object t)
        // 但 Object test(Object t) 会作为一个**桥接方法**，调用一个 String test(String t)


        // 泛型的使用有若干限制：
        // 1.<T>不能是基本类型，例如int，因为实际类型是Object，Object类型无法持有基本类型
//        A<int>a = new A<int>(); // 报错

        // 2.无法获取带泛型的类型. 只能判断是不是原始类型，里面的具体类型是不支持的：
        A<String> s = new A<>(String.class);
//        System.out.println(s instanceof A<String>);       // 报错
        System.out.println(s instanceof A); // 始终为 true
        //因为并不存在 A<String>.class ,只有唯一的 A.class

        // 3. 泛型变量不支持创建数组
//        A<String>[] test1 = new A<String>[10];            // 报错
        A<String>[] test2 = new A[10];
        test2[1] = new A<String>(String.class);

        // 4. 不能实例化 T 类型。只能通过在构造函数实参传入对应的 Class 类。




    }


    public static class A<T> {
        T score;

        // 错误的 T 的实例化
//        public A(T score) {
//            score = new T();    // 不能直接实例化 ，因为实际会编译为 score = new Object().而创建 new Pair<String>() 和创建 new Pair<Integer>() 就全部成了 Object
//            this.score = score;
//        }

        // 正确的 T 的实例化（反射,要求构造函数的入参是 Class 类型）
        public A(Class<T> cls) throws InstantiationException, IllegalAccessException {
            score = cls.newInstance();      // 借助 Class<T> 参数并通过反射来实例化T类型，使用的时候，也必须传入Class<T>。
        }




        public T test(T t) {
            System.out.println(t);
            return t;
        };


    }

    public static class B extends A<String> {
        public B(Class<String> cls) throws InstantiationException, IllegalAccessException {
            super(cls);
        }

        @Override
        public String test(String t) {
            System.out.println("来自类型B, " + t);
            return t;
        }
    }
}
