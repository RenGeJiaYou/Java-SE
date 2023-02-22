package com.core.str;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

// BigInteger & BigDecimal
// Java 的 long 类型有 8 字节，64 bit.但也可能面临不够用的情况
// 解决方案是用 int[] 模拟一个非常大的整数
public class big_number {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);

        BigInteger bi = new BigInteger("456789123456789");
        System.out.println(bi.pow(5));

        // BigInteger 不能用运算符，只能用实例方法
        BigInteger a = new BigInteger("465000000000000000000000000000");
        BigInteger sum = a.add(new BigInteger("5"));
        System.out.println(sum);

        // 类似地，BigDecimal 模拟一个非常大的浮点数
        BigDecimal bd = new BigDecimal("123.123456789");
        System.out.println(bd.multiply(bd));

        // 常用方法
        System.out.println(bd.scale()); //9   scale() 表示小数的位数
        System.out.println(bd.intValue()); //123   intValue() 返回浮点数的整数部分

        BigDecimal bd1 = new BigDecimal("12.3000");
        System.out.println(bd1.stripTrailingZeros()); //12.3  stripTrailingZeros()去除小数部分末尾的 0

        BigDecimal bd2 = bd.setScale(4, BigDecimal.ROUND_HALF_UP); // 精度取 4 位，多余部分四舍五入
        BigDecimal bd3 = bd.setScale(4, RoundingMode.DOWN); // 精度取 4 位，多余部分直接截断
        System.out.println(bd2);// 12.1235
        System.out.println(bd3);// 12.1234

        // BigDecimal 只有在做除法时会发生除不尽，因此需要特别考虑精度
        BigDecimal d = bd.divide(bd1,5,RoundingMode.DOWN);
        System.out.println(d);

        // BigDecimal 的比较：equals() 要求两个小数的 scale 相同，因此可改用 compareTo()
        System.out.println(bd.equals(bd2));
        System.out.println(bd.compareTo(bd2));//-1  表示 bd < bd2


    }
}
