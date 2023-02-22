public class test {
    public static void main(String[] args) {

        Integer[] arr = {1, 2, 3, 4, 5};
        Integer[] arr2 = new Integer[10];
        foo(arr);
        for (Integer a : arr) {
            System.out.println(a);
        }

        Integer b = 3;
        bar(b);
        System.out.println(b); // 3
    }

    static void foo(Integer[] a) {
        a[2] = 99;
    }
    static void bar(Integer _b){
        _b=6;

        // 1.将实参 Integer b 传入bar() 时，变量名 b 和 _b 都指向同一个对象
        // 2."_b=6" 使 _b 指向一块新的对象（int 到 Integer 自动装箱了）
        // 3.bar() 退出，_b 所指对象被垃圾回收
        // 4.b 变量及其指向的对象不变
    }
}


