package com.mayao.jdk.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/16
 */
@Slf4j
public class Worker implements Runnable {

    private CountDownLatch countDownLatch;

    public Worker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try{
            //随机睡眠几秒
            Thread.sleep(getRandomSeconds());
            System.out.println("Counting down："+ Thread.currentThread().getName());

            this.countDownLatch.countDown();
        }catch ( Exception ex ){
//            Thread.currentThread().interrupt();
            log.error("线程发生异常了！",ex);
        }
    }

    /**
     * 随机返回  0~9999
     * @return
     */
    private long getRandomSeconds(){
        Random random = new Random();
        return Math.abs(random.nextLong() % 10000);
    }

}
