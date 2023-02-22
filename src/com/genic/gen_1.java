package com.genic;

import java.util.ArrayList;
import java.util.List;

// 如果没有泛型，类/方法 有多少种数据类型需要处理，就需要声明多少个大同小异的 类/方法
// 泛型的出现，不管有多少种类型，定义的时候都只需要写一份。
public class gen_1 {
    public static void main(String[] args) {
        /*需求分析:
         * 设计一个 Score 类，成员字段 value 既能接收（95、82.5）数值型成绩，也能接收（优、良、中、差）字符型成绩
         * 解决方案一：Score1
         *   value 用 Object 类型，这样一来就能接收各种类型数据
         * */
        Score1 s1 = new Score1("OS", "1", 35);
        Score1 s2 = new Score1("Net", "2", "优秀");
        Integer iScore = (Integer) s1.getValue();
        String sScore = (String) s2.getValue();
        /*使用 Object 类型装载各种数据的缺点在于：
         *   编译期不能良好地判断类型。不知道传来的数据具体是什么类型，还得额外写判值代码
         * */


        /*解决方案二：Score2
         *   使用泛型，在 类名后添加<T>。在声明时不具体指定类型，而是在使用时具体传入。
         *   注意：
         *      1.调用时，右值的 <Integer> 可以简化为 <>。因为左值的泛型参数一定符合右值的泛型参数，既然只有这一种可能性，就可以省略。
         *      2.声明时，<T>字段在构造函数中不能直接实例化
         * */
        Score2<Integer, Integer> score1 = new Score2<>("DS", 3, 99);
        Score2<String, Integer> score2 = new Score2<>("cs", 4, "优秀");

        /* 调用时，除了传入确定的类型，也可以传入 ? 通配符表示任意类型,实际是 Object 实现的 */
        Score2<?, Integer> score3 = new Score2<>("BA", 5, Boolean.FALSE);

        /*静态方法，无法使用来自 class 类名 <T> 所传递的泛型类型变量*/

        /*泛型类型变量必须是 引用类型，像 int,double 这种基本类型是不行的。因为类型擦除会把实际传入的类型化为 Object ,而 任何一种基本类型都不是 Object 的子类
        * 但是，基本类型的数组属于引用类型，因此是编译合法的
        * */
        Score2<Double, Integer> score4 = new Score2<>("cc", 5, 63.1);   // √
//        Score2<double, int> score5 = new Score2<>("cc", 5, 63.1);   // ×
        Score2<double[],int[]> score6 = new Score2<>("ov", new int[]{2, 3, 4}, new double[]{33.1, 22.5}); // √


        // 泛型的继承问题：
        // 总结：A 是 B 的父类，但 G<A> 和 G<B> 是并列关系。如果希望按继承的方式运行，参见gen_4
        List<Integer> arr = new ArrayList<Integer>();           // ArrayList<T> 实现了接口 List<T> , 因此 ArrayList<Integer> 能够以向上转型的方式赋值给 List<Integer>
//        ArrayList<Number> arr2 = new ArrayList<Integer>();    // 内层的泛型参数的继承关系，不等于外层类的继承关系

    }

}

class Score1 {
    String name;
    String id;
    Object value;

    public Score1(String name, String id, Object value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}

class Score2<T, U> {        // <> 声明1 ~ N 个类型变量
    String name;
    T value;
    U id;

    public Score2(String name, U id, T value) {
        this.name = name;
        this.id = id;
        this.value = value;
//        new T[10];  泛型类型变量不得直接实例化
    }


    public T getValue() {
        return value;
    }

    // 普通的成员方法，返回值和参数内都可以使用泛型变量
    U test(T key) {
        System.out.println(key);
        return id;
    }

    ;

    // 静态方法无法（直接）使用泛型变量
//    static U test2(){
//        return id;
//    }

    // 静态方法在 static 关键字后加<E> 可以使用泛型。不管这时的泛型变量 E 和 Score2 类传入的泛型<T,U> 毫无关系
    static <E> void test3(E key) {
        System.out.println(key);
    }
}
