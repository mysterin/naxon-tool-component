package com.naxon.tool.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类
 */
public class DateUtils {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static Map<String, DateTimeFormatter> dateTimeFormatterMap = new HashMap<>();

    public static LocalDateTime parse(String text, String pattern) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(text, dateTimeFormatter);
        return dateTime;
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);
        String format = dateTime.format(dateTimeFormatter);
        return format;
    }

    public static LocalDateTime getNow() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    public static LocalDateTime getYesterday() {
        LocalDateTime now = getNow();
        LocalDateTime yesterday = now.plusDays(-1);
        return yesterday;
    }

    public static LocalDateTime getTomorrow() {
        LocalDateTime now = getNow();
        LocalDateTime tomorrow = now.plusDays(1);
        return tomorrow;
    }

    /**
     * 比较时间，dateTime1<dateTime2: -1; dateTime1=dateTime2: 0; dateTime1>dateTime2: 1
     * @param dateTime1
     * @param dateTime2
     * @return
     */
    public static Integer compareDateTime(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        if (dateTime1.isBefore(dateTime2)) {
            return  -1;
        } else if (dateTime1.isEqual(dateTime2)) {
            return 0;
        } else {
            return 1;
        }
    }

    public static LocalDateTime covertLocalDateTime(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }

    public static Date covertDate(LocalDateTime dateTime) {
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterMap.get(pattern);
        if (dateTimeFormatter == null) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            dateTimeFormatterMap.put(pattern, dateTimeFormatter);
        }
        return dateTimeFormatter;
    }

}
