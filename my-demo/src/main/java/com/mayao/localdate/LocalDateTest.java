package com.mayao.localdate;

import java.time.Clock;
import java.time.Instant;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/4
 */
public class LocalDateTest {


    public void clockTest(){
        Clock.systemDefaultZone().instant().toEpochMilli();
        Clock.systemUTC().instant().toEpochMilli();
        Instant.now().toEpochMilli();
        org.joda.time.Instant.now().getMillis();
    }

}
