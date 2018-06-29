package com.mayao.jdk.langs;

import com.mayao.blog.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/27
 */
@Slf4j
public class RandomTest {

    @Test
    public void intTest(){
        log.info("Random随机测试。。");
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(10);
            System.out.print(index);
        }
        System.out.println("RandomTest.intTest");
        log.info("Math随机测试。。");
        for (int i = 0; i < 10; i++) {
            double no = Math.random();
            System.out.println(no);
        }
    }

    @Test
    public void exceptionTest(){
        User user = null;
        if( user == null){
            log.info("null的实体类放前面判断可以");
        }else{
            log.info("null的实体类放前面判断可以else");
        }
    }


}
