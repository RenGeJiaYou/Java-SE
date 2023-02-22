package com.test.entity;

public class Student extends Person {

    public Student(String name, int age, String sex,int score) {
        super(name, age, sex);
        this.score = score;
    }
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;

    }
     public void testPriv(){
         System.out.println(age);   // public 属性    可被子类访问
         System.out.println(car);   // 默认属性        可被子类访问
         System.out.println(house); // protected 属性 可被子类访问
//         System.out.println(cash);  // private 属性   不可被子类访问

     }

     // Override，子类中有作用不同的同名函数。添加注解是为了让编译器帮助检查,并不必须。
    @Override
    public void Hello(){
        System.out.println("Student Hello");
    }

}
