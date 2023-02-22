package com.date_time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

// 新日期 API ZonedDateTime、ZoneId:
// 等价于 LocalDateTime + 时区 ZoneId，因此可以和时间戳转换
public class dt_3 {
    public static void main(String[] args) {
        // 1.同一时刻的不同时区
        ZonedDateTime zdj = ZonedDateTime.now();    // 默认时区,[Asia/Shanghai]
        ZonedDateTime zdj_ny = ZonedDateTime.now(ZoneId.of("America/New_York"));    // 指定时区

        System.out.println(zdj);    // 2023-02-17T15:33:12.206+08:00[Asia/Shanghai]
        System.out.println(zdj_ny); // 2023-02-17T03:08:21.779-05:00[America/New_York]


        // 2.同一时间附加不同时区，变成不同的时刻
        // LocalDateTime 附加 ZoneId,即可变为 ZonedDateTime .
        // 具体过程为：对 LocalDateTime 对象调用 atZone()
        LocalDateTime ldt = LocalDateTime.of(2019, 9, 15, 15, 16, 17);
        ZonedDateTime zdt_sh = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zdt_ph = ldt.atZone(ZoneId.of("America/Phoenix"));

        System.out.println(zdt_sh); // 2019-09-15T15:16:17+08:00[Asia/Shanghai]
        System.out.println(zdt_ph); // 2019-09-15T15:16:17-07:00[America/Phoenix]


        // 3.时区转换，（要求时间戳不变）
        // 调用 ZonedDateTime 对象的 withZoneSameInstant 方法
        ZonedDateTime zbj = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt_pa = zbj.withZoneSameInstant(ZoneId.of("Europe/Paris"));

        System.out.println(zbj);        // 2023-02-17T17:11:32.209+08:00[Asia/Shanghai]
        System.out.println(zdt_pa);     // 2023-02-17T10:11:32.209+01:00[Europe/Paris]
    }
}
