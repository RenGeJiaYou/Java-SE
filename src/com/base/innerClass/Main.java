package com.base.innerClass;

//学习内部类,即定义在另一个类的内部
public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer("Nested");
        Outer.Inner inner = outer.new Inner();  // 通过外部类的引用变量 new 出内部类的实例对象

    }
}

class Outer {
    private String name;

    public Outer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void hi(){
        System.out.println("Outer private methods");
    }
    // 内部类,可以使用外部类的 private 字段或方法
    class Inner{
        void hello(){
            System.out.println("Hello,"+Outer.this.name);
            Outer.this.hi();
        }
    }

}
