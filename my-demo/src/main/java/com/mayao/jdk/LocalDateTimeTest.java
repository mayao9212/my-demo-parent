package com.mayao.jdk;

import org.junit.Test;

import java.time.LocalDate;

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



}
