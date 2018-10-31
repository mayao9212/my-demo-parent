package com.mayao.jodatime;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/28
 */
@Slf4j
public class DateTest {

    private static final String SIMPLE = "yyyy-MM-dd hh:ss:mm";

    @Test
    public void validTimeTest(){

        Date date = new Date();
        log.info("当前时间：{}",new DateTime(date).toString(SIMPLE));
        int length = 7;
        date = new DateTime(date).plusDays(length).minusSeconds(1).toDate();
        log.info("格式化：{}",new DateTime(date).toString(SIMPLE));

        Date date2 = LocalDate.now().toDate();
        log.info("当前时间：{}",new DateTime(date2).toString(SIMPLE));
        String date3 = LocalDate.now().toString();
        log.info(date3);

        DateTime date4 = DateTime.parse(date3).plusDays(length+1).minusSeconds(1);
        log.info("date4:{}",date4.toString(SIMPLE));

        DateTime date5 = DateTime.now().plusDays(length+1).minusSeconds(1);
        log.info("date5:{}",date4.toString(SIMPLE));

    }

    @Test
    public void localDateTest(){

        Date date = LocalDate.now().toDate();
        log.info(date.toString());

    }

    @Test
    public void isToday(){

        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        LocalDate fromDate = dateTime.toLocalDate();
        log.info(fromDate.toDate().toString());
        log.info(""+LocalDate.now().toDate().compareTo(fromDate.toDate()));


    }



}
