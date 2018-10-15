package com.mayao.jdk;

import org.junit.Test;

import java.io.File;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * function ：1.8 之后替代Date的接口
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/2/24
 */
public class LocalDateTimeTest {


    @Test
    public void LocalDateTest(){

        LocalDate localDate = LocalDate.now();
        //2018-02-24
        System.out.println(localDate);
        localDate.atStartOfDay();

//        ChronoUnit.DAYS

    }


    @Test
    public void localDateTimeTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String ss = localDateTime.format(formatter);
        System.out.println(ss);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void substringTest(){
        String enshPath="F:\\silent-installer\\installers\\1223\\123\\sh\\start.sh";

        String shellName = enshPath.substring(enshPath.lastIndexOf(File.separator)+1,enshPath.length());
        System.out.println(shellName);
    }




}
