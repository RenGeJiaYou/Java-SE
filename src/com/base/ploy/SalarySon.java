package com.test.ploy;

// 测试 爷类引用变量能不能指向孙类实例对象（√）
public class SalarySon extends Salary {
    public SalarySon(double income) {
        super(income);
    }


    public double getTax() {
        return super.getTax();
    }

    public void hi() {
        System.out.println("private ");
    }

    // 内部定义的嵌套类可以访问 private 成员函数
    static class Inner {
        public void hi() {
            this.hi();
        }
    }
}
