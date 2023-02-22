package com.test.package_learn;
// 研究包和作用域

import com.test.ploy.*;
import com.test.static_learn.Person;

public class Main {
    public static void main(String[] args) {
        // 调用不同包中的同名类，使用具体的包名+类名
        com.test.static_learn.Person p = new Person(1, 2, 3);

        com.test.entity.Person p2 = new com.test.entity.Person("a", 13, "male");

        // 在 main （而不能是普通类体中）中调用别的包中的类，被调类需要是 public ,并且在包中是单独的类文件
        Income inc = new Income(23.2);
        inc.getTax();
        SalarySon s = new SalarySon(123.5);
        s.hi();
        // ↓ 如果该类的访问权限是 default,没有声明为 public ，那么将不可在包外调用。
//        StateCouncilSpecialAllowance sss = new StateCouncilSpecialAllowance();
    }


}
