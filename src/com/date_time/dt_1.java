package com.date_time;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Date & Calender
public class dt_1 {
    public static void main(String[] args) {
        // 1.同一个值有不同的展示方式
        int n =123400;
        System.out.println(n);                                                          // 123400
        System.out.println(Integer.toHexString(n));                                     // 1e208
        System.out.println(NumberFormat.getCurrencyInstance(Locale.CHINA).format(n));   // ￥123,400.00

        // 2. 时间戳：计算机的「时刻」只有一个存储格式，即 1970 年 1 月 1 日零点到当前的总[毫]秒数，是一个整数
        // 但可以有不同的展示格式
        System.out.println(System.currentTimeMillis()); // 返回毫秒数 1676533623546

        // Java 的时间日期 API 分新旧两个部分
        // 旧：java.util 的 Date、Calendar、TimeZone
        // 新：java.time 的 LocalDateTime、ZoneDateTime、ZoneId

        // 一、旧 API
        // 1. Date 基本用法
        // 获取当前时间
        Date d1 = new Date();
        System.out.println(d1.getTime());
        System.out.println(d1.getYear() + 1900);    // 必须加上1900
        System.out.println(d1.getMonth() + 1);      // 0~11，必须加上1
        System.out.println(d1.getDate());           // 1~31，不能加1
        System.out.println(d1.toString());          // 转换为 String     Thu Feb 16 16:06:59 CST 2023
        System.out.println(d1.toGMTString());       // 转换为 GMT 时区    16 Feb 2023 08:06:59 GMT
        System.out.println(d1.toLocaleString());    // 转换为本地时区      2023-2-16 16:06:59

        // 自定义日期格式
        Date d2 = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日 - HH:mm:ss");
        System.out.println(dateFormat.format(d2));

        // 预定义数据格式
        // 一言以蔽之，每个不同的字母（区分大小写）代表某个时间对象，该字母的「重复次数」决定了它是以全称、简写或补0的方式打印
        // 详见 https://www.matools.com/api/java8 SimpleDateFormat
        SimpleDateFormat f1 = new SimpleDateFormat("EE,MM,ddd");        // 星期四,02,016
        SimpleDateFormat f2 = new SimpleDateFormat("EEE,MMM,dd");       // 星期四,二月,16
        System.out.println(f1.format(d2));
        System.out.println(f2.format(d2));


        // 2. Calendar 在 Date 的基础上，多了一个日期运算功能
        Calendar c1 = Calendar.getInstance();   // 已经获得当前时间
        c1.get(Calendar.YEAR);                  // 其它时间对象均有相关字段
        c1.clear();                             // 清除通过已获得的日期数据
        c1.set(Calendar.MONTH, 8);              // 设置9月:注意8表示9月:
        c1.set(Calendar.HOUR,16);               // etc.

        // 3. Calendar 对象调用 getTime() -> Date 对象
        Calendar c2 = Calendar.getInstance();
        Date d3 = c2.getTime();

        // 4. 时区
        // Java 中，一个时区用唯一的字符串表示
        // Calendar 和 SimpleDateFormat 都能调用 setTimeZone() 传入一个时区对象
        System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));
        // 有了时区，就能更换区域时间
        Calendar c3 = Calendar.getInstance();
        c3.clear();
        c3.set(2023,2,17,9,41,37);

        c3.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设定上海时区

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日 - HH:mm:ss"); //注意，时区信息存储在 SimpleDateFormat 中
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));// 重新设为纽约时区
        System.out.println(sdf.format(c3.getTime()));

        // 5.日期的简单加减
        c3.add(Calendar.DAY_OF_MONTH,1);
        c3.add(Calendar.HOUR,-5);
        System.out.println(dateFormat.format(c3.getTime()));


    }
}
