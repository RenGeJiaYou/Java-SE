package com.test.ploy;

/*
 * 一个解释多态的例子
 * 基类为 Income       有一个计税函数 double getTex() 税率：10%
 * 超类为 Salary       override 了 double getTex()  税率：5000以上部分征税 20%
 * 超类为 Allowance    override 了 double getTex()  税率：0
 * */
public class Main {
    public static void main(String[] args) {
        /*
         * override 的一个例子
         * */
        Income[] incomes = new Income[]{
                new Income(1000),
                new Salary(1000),
                new StateCouncilSpecialAllowance(1000),
                new SalarySon(1000)
        };
        System.out.println(totalTax(incomes));

        Salary s = new Salary(123);

    }

    public static double totalTax(Income... incomes) {
        double total = 0;
        for (Income income : incomes) {
            total+= income.getTax(); // 引用变量虽然是 Income 基类，但是可以指向任意的超类（及超类的超类）实例对象，并调用子类的 override 函数
        }
        return total;
    }



}
