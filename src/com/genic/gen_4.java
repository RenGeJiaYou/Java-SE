package com.genic;

// 泛型的界限
// 在使用泛型时，不希望能传入全部类型，而是指定的若干种类型。此时便要考虑「泛型的界限」

// 泛型的界限基于类的继承树，并且是 ≥ 和 ≤ （而非 ＞和 ＜）的。
// 限定一个上界，意味着允许进入的类必须是这个上界类及其后代类。使用 extends 关键字
// 限定一个下界，意味着允许进入的类必须是这个下界类及其祖先类。使用 super 关键字
public class gen_4 {
    public static void main(String[] args) {
        // 一、限定上界
        // 1.在类定义时限定上界
        Score<Integer> a = new Score<>(10);   //泛型参数 T extends Number 后，可传入任何以 Number 为祖先的类。达到“向上转型”的效果

        // 2.在赋值时限定上界
        Score<? extends Number> b1 = new Score<Double>(32.3);   // 报错
        Score<? extends Integer> b2 = new Score<Integer>(10);

        // 使用类似<? extends Number>通配符作为方法参数时表示:
        //      方法内部可以调用获取 Number 引用的方法，例如：Number n = obj.getFirst();
        // 3.允许调用 get() 方法获取 Integer 的引用
        Score<Number> d = new Score<>(10);
        Number in = d.getScore();   // 其内部调用了一个 Number 类的方法，返回了一个 Number 变量。

        // 4.不允许调用 set(? extends Integer) 方法并传入任何 Integer 的实例
        Score<Integer> d1 = new Score<>(10);
        Score<Integer> d2 = new Score<>(20);
        int sum = Score.add(d1, d2);
        System.out.println(sum);

        // 二、限定下界,右值传入的必须是 Integer 及其父类
        Under<? super Integer> c = new Under<>(10);

//        Score<? extends Number> e = new Score<Number>(20);
//        e.setScore((Integer)20); // 报错，因为在规定上界后，实例 e 可能接收基于 Number 类的各种类（Integer、Double、Short）的值到 字段 field 。因此在赋值时不能输入某一个具体类型。
        Score<? super Integer> e = new Score<>(30);
        e.setScore(60);     // 传入一个数字，可以通过向上转型实际按照 Object 接收。

        Score<Number> e1 = new Score<>(126);
        Score<Integer> e2 = new Score<>(789);
        Score.setSame(e1,50);
        Score.setSame(e2,600);


    }


}

// 使用 extends 限定上界：
// 若想让可选的泛型局限在 数字类型中，让类型参数 T 继承所有数字类型的父类
class Score<T> {
    T score;

    public Score(T score) {
        this.score = score;
    }

    // 1.可读：限定上界 Number 后，方法内部可以调用获取 Number 引用变量的 get() 方法
    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }

    // 2.不可写：限定上界 Number 后，方法内部不可调用 Number 引用变量的 set() 方法传入
    static int add(Score<? extends Number> d1, Score<? extends Number> d2) {
//        d1.setScore(new Integer(123));  // 报错
//        d2.setScore(new Integer(456));  // 报错
        // 以上均报错。但 add() 定义时的参数 d1,d2 是 Score<? extends Number>; add() 调用时传入的是 Score<Integer> 实例d1,d2;为何d1,d2的成员方法不能传入 一个 Integer 变量？
        // 很简单，若 add() 下次传入的实参是 Score<Double> 等其它类型，虽然满足参数定义的 Score<? extends Number> ,但无法在 setScore() 等成员方法中传入 Integer 实例

        return d1.getScore().intValue() + d2.getScore().intValue();
    }

    static void setSame(Score<? super Integer> d,Integer n){
        d.setScore(n);
    }


}

class Under<T> {
    T field;

    // 限定类型的上界后，该类型的字段自动获取上界的方法
    public T getField() {
//        field.equals(); // 未规定上界，因此是一个 Object 对象，所能调用的只有 Object 类的方法
        return field;
    }

    public Under(T fid) {
        this.field = fid;
    }
}
