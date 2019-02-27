package com.mayao.jodatime;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void dateTimeFormateTest(){
        String s = org.joda.time.LocalDateTime.now().minusDays(1).toString("yyyy-MM-dd");
        System.err.println(s);
    }

    private static final String DATE_FORMATE = "yyyyMMddHHmmssSSS";
    /**
     * 生成商户业务流水
     * @return
     */
    @Test
    public void getOutBizNo(){
        log.info(FastDateFormat.getInstance(DATE_FORMATE).format(new Date())+"syk");
    }

    /**
     * 把当前时间格式为指定格式
     */
    @Test
    public void test5(){
        //获得当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format = ldt.format(dtf);
        System.out.println(format);
    }

    /**
     * 把指定字符串格式化为日期
     */
    @Test
    public void test6(){
        String str1="2018-07-05 12:24:12";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(str1, dtf);
        System.out.println(localDateTime);

        //to Date
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        Date date = Date.from(instant);
        System.out.println("DateTest.test6:"+date);

        //
        System.out.println(parseDate(str1,"yyyy-MM-dd HH:mm:ss"));
    }


    public static Date parseDate(String date,String format) {
        //时间转换
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = java.time.LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format)).atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    @Test
    public void jsonTest(){
        Map map = new HashMap();
        map.put("ids",Arrays.asList(21,22));
        map.put("feedbackRemark","test");
        System.out.println("DateTest.jsonTest："+JSON.toJSONString(map));
    }

    @Test
    public void expireMinutes(){
        DateTime dateTime =  DateTime.now().plusDays(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        System.out.println("DateTest.expireMinutes："+dateTime);
        Interval interval = new Interval(System.currentTimeMillis(),dateTime.getMillis());
        Period period = interval.toPeriod();
        System.out.println("DateTest.expireMinutes："+(period.getHours()*60+period.getMinutes()+1));


        DateTime dateTime1 = DateTime.parse("2018-12-31 12:23:45",DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));

        long two = dateTime1.getMillis();

        long hours = (dateTime1.getMillis() - System.currentTimeMillis())/(1000*60*60);
        System.out.println("DateTest.expireMinutes："+(hours+1));

    }


}
