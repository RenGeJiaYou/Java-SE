package com.date_time;


import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

// Instant 是 Java 表示时间戳的实体类
// 新旧两套 API 的转换工作
public class dt_5 {
    public static void main(String[] args) {
        // 效果类似
        System.out.println(Instant.now().getEpochSecond());  // 1676626680      秒
        System.out.println(System.currentTimeMillis());      // 1676626602749   毫秒

        // Instant 加上时区 ZoneId，就是 ZonedDateTime

        // 1.旧 API -> 新 API  均可采用旧类的 toInstant()
        // (1).Date -> Instant
        Instant i1 = new Date().toInstant();

        // (2).Calendar -> Instant -> ZonedDateTime
        Calendar c = Calendar.getInstance();
        Instant i2 = c.toInstant();
        ZonedDateTime zdt = i2.atZone(c.getTimeZone().toZoneId());


        // 2.新 API -> 旧 API  借助 long 型时间戳做中转
        ZonedDateTime zdt2 = ZonedDateTime.now();
        long ts = zdt2.toEpochSecond() * 1000;  // ZonedDateTime -> long:
        Date date = new Date(ts);               // long -> Date

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeZone(TimeZone.getTimeZone(zdt.getZone().getId()));
        cal.setTimeInMillis(zdt2.toEpochSecond() * 1000);

    }
}
