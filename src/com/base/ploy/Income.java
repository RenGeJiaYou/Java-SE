package com.test.ploy;


// 基本收入，税率 10%
public class Income {
    protected double income;

    public Income(double income) {
        this.income = income;
    }


    public double getTax() {
        return income * 0.1;
    }
}





