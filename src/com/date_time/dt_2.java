package com.date_time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

// 新日期 API LocalDateTime:
// 本地日期和时间：      LocalDateTime,LocalDate,LocalTime
// 带时区的日期和时间：   ZonedDateTime
// 时刻：              Instant
// 时区：              ZoneId,ZoneOffset
// 时间间隔：           Duration
// 格式化类型：         DateTimeFormatter （取代 SimpleDateFormat）
public class dt_2 {

    public static void main(String[] args) {
        // 1.获取当前时间
        LocalDateTime dt = LocalDateTime.now();
        // 为保持时间一致，最好从 LocalDateTime 对象提取，而不是单独调用 LocalDate 、LocalTime 的 now()
        LocalDate d = dt.toLocalDate();
        LocalTime t = dt.toLocalTime();
        System.out.println(dt);

        // 2.用 .of()设置日期
        LocalDate d2 = LocalDate.of(2023, 1, 7);
        LocalTime t2 = LocalTime.of(15, 16, 17);
        LocalDateTime dt2 = LocalDateTime.of(d2, t2);
        // 由于新 API 严格遵守 ISO 8601 标准，因此可以用 parse() 传入标准格式的字符串设置时间
        LocalDateTime dt3 = LocalDateTime.parse("2023-02-17T10:28:20");
        LocalDate d3 = LocalDate.parse("2019-11-19");
        LocalTime t3 = LocalTime.parse("15:16:17");
        System.out.println(dt3.getDayOfMonth());

        // 3.用 DateTimeFormatter 自定义输出格式
        // 自定义格式化:
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy 年 MM 月 dd - HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
        // 用自定义格式解析为 long 时间戳：
        LocalDateTime dt4 = LocalDateTime.parse("2023 年 02 月 17 - 10:41:58", dtf);
        System.out.println(dt4.getDayOfWeek());

        // 4.plusXxxs() / minusYyys() 提供链式调用完成日期的加减
        LocalDateTime dt5 = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println("原始时间：\t\t\t"+dt5);
        // 加 5 天减 3 小时
        System.out.println("加 5 天减 3 小时：\t"+dt5.plusDays(5).minusHours(18));


        // withZzzs() 不做增量的改变，直接修改指定值
        System.out.println("with() 函数直接修改值："+ dt5.withSecond(12));

        // ※ 注意，plus() / minus() / with()
        // (1).都不是修改原始值，而是返回修改后的新值
        // (2).修改月份时会相应地调整日期，如 10.31 会自动变为 9.30


        // 5. 使用通用的 with() 进行复杂的日期运算
        // 本月第一天:
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        System.out.println("本月第一天0:00时刻:\t "+firstDay);

        // 本月最后1天:
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);

        // 下月第1天:
        LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(nextMonthFirstDay);

        // 本月第一个周一：
        LocalDate firstWeekday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstWeekday);


        // 6. isBefore()、isAfter() 判断两个日期的先后
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2020,12,30,02,03,04);
        System.out.println(now.isBefore(target));// false
        System.out.println(now.isBefore(LocalDateTime.of(2024, 12, 12, 12, 12, 12)));   // true

        // LocalDateTime 无法和时间戳转换，因为没有时区，不能确定具体是哪一时刻


        // 7.Duration 和 Period
        // Duration 表示两个时刻的间隔；Period 表示两个日期间的天数
        // 用 "P...T..." 表示时，"P...T"之间表示日期间隔，"T"后面表示时间间隔；用"PT..."表示时，说明只有时间间隔
        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration du = Duration.between(start,end);
        System.out.println(du); // PT1235H10M30S 表示 1235 小时 10 分 30 秒

        Period p = LocalDate.of(2018,03,20).until(LocalDate.of(2023,02,17));
        System.out.println(p);  // P4Y10M28D 表示 4 年 10 个月 28 天

    }
}
