package com.naxon.tool.common;

import com.naxon.tool.common.constant.DatePatternConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author xiaobin.lin
 * @Description
 * @createTime 2020-09-07 18:39:23
 */
@Slf4j
class DateUtilsTest {

    @Test
    void parse() {
        String text = "2020-09-07 18:42:39";
        LocalDateTime localDateTime = DateUtils.parse(text, DatePatternConstant.DATETIME_FORMAT);
        log.debug(localDateTime.toString());
    }

    @Test
    void format() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = DateUtils.format(localDateTime, DatePatternConstant.DATETIME_FORMAT);
        log.debug(format);
        format = DateUtils.format(localDateTime, DatePatternConstant.DATE_FORMAT);
//        assertEquals("2020-09-07", format);
    }

    @Test
    void getNow() {
        LocalDateTime now = DateUtils.getNow();
        log.debug(now.toString());
    }

    @Test
    void getYesterday() {
        LocalDateTime yesterday = DateUtils.getYesterday();
        log.debug(yesterday.toString());
    }

    @Test
    void getTomorrow() {
        LocalDateTime tomorrow = DateUtils.getTomorrow();
        log.debug(tomorrow.toString());
    }

    @Test
    void compareDateTime() {
        LocalDateTime yesterday = DateUtils.getYesterday();
        LocalDateTime tomorrow = DateUtils.getTomorrow();
        Integer result = DateUtils.compareDateTime(yesterday, tomorrow);
//        assertEquals(-1, result);

        result = DateUtils.compareDateTime(tomorrow, yesterday);
//        assertEquals(1, result);

        result = DateUtils.compareDateTime(tomorrow, tomorrow);
//        assertEquals(0, result);
    }

    @Test
    void covertLocalDateTime() {
        Date date = new Date();
        LocalDateTime localDateTime = DateUtils.covertLocalDateTime(date);
        log.debug(localDateTime.toString());
    }

    @Test
    void covertDate() {
        LocalDateTime now = DateUtils.getNow();
        Date date = DateUtils.covertDate(now);
        log.debug(date.toString());
    }

    @Test
    void getDateTimeFormatter() {
    }
}