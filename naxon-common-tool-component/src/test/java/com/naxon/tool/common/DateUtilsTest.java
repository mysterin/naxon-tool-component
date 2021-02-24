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
        LocalDateTime localDateTime = DateUtil.parse(text, DatePatternConstant.DATETIME_FORMAT);
        log.debug(localDateTime.toString());
    }

    @Test
    void format() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = DateUtil.format(localDateTime, DatePatternConstant.DATETIME_FORMAT);
        log.debug(format);
        format = DateUtil.format(localDateTime, DatePatternConstant.DATE_FORMAT);
//        assertEquals("2020-09-07", format);
    }

    @Test
    void getNow() {
        LocalDateTime now = DateUtil.getNow();
        log.debug(now.toString());
    }

    @Test
    void getYesterday() {
        LocalDateTime yesterday = DateUtil.getYesterday();
        log.debug(yesterday.toString());
    }

    @Test
    void getTomorrow() {
        LocalDateTime tomorrow = DateUtil.getTomorrow();
        log.debug(tomorrow.toString());
    }

    @Test
    void compareDateTime() {
        LocalDateTime yesterday = DateUtil.getYesterday();
        LocalDateTime tomorrow = DateUtil.getTomorrow();
        Integer result = DateUtil.compareDateTime(yesterday, tomorrow);
//        assertEquals(-1, result);

        result = DateUtil.compareDateTime(tomorrow, yesterday);
//        assertEquals(1, result);

        result = DateUtil.compareDateTime(tomorrow, tomorrow);
//        assertEquals(0, result);
    }

    @Test
    void covertLocalDateTime() {
        Date date = new Date();
        LocalDateTime localDateTime = DateUtil.covertLocalDateTime(date);
        log.debug(localDateTime.toString());
    }

    @Test
    void covertDate() {
        LocalDateTime now = DateUtil.getNow();
        Date date = DateUtil.covertDate(now);
        log.debug(date.toString());
    }

    @Test
    void getDateTimeFormatter() {
    }
}