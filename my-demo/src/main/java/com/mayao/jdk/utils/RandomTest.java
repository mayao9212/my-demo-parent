package com.mayao.jdk.utils;

import org.junit.Test;

import java.util.Random;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/17
 */
public class RandomTest {

    @Test
    public void throwException(){
        Random random = new Random();
        int i=0;
        while (i<20){
            System.out.println(random.nextInt(5));
            i++;
        }
    }

}
