package com.date_time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// DateTimeFormatter
// (1).是常量
// (2).是线程安全的
public class dt_4 {
    public static void main(String[] args) {
        // 1.DateTimeFormatter 的声明
        // .ofPattern() 传入自定义格式化字符串
        // .format(l) 传入待格式化的日期对象
        LocalDateTime l = LocalDateTime.of(2012,12,12,12,12,12);

        DateTimeFormatter f_china = DateTimeFormatter.ofPattern("E, yyyy-MMMM-dd HH:mm", Locale.CHINESE);
        System.out.println(f_china.format(l));  // 星期三, 2012-十二月-12 12:12

        DateTimeFormatter f_usa = DateTimeFormatter.ofPattern("E, yyyy-MMMM-dd HH:mm", Locale.US);
        System.out.println(f_usa.format(l));    // Wed, 2012-December-12 12:12

        DateTimeFormatter f_jp = DateTimeFormatter.ofPattern("E, yyyy-MMMM-dd HH:mm", Locale.JAPANESE);
        System.out.println(f_jp.format(l));     // 水, 2012-12月-12 12:12

    }

}
