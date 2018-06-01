package com.mayao.stream;

import org.junit.Test;

import java.util.Optional;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/30
 */
public class OptionalTest {

    @Test
    public void nullTest(){
        String a = null;
        String b = "1234";
        Optional optional = Optional.ofNullable(a);
        optional.orElse(b);
        System.out.println(optional.get());
    }

}
