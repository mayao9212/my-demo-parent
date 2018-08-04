package com.mayao.jdk.thread.latch;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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

            //countDownLatch.countDown()之前异常测试,次数不会减少。。需要放到finally里面
            throwException();


//            System.out.println(this.countDownLatch.getCount());

//            synchronized (this.countDownLatch){
//                if( 0==this.countDownLatch.getCount() ){
//                    System.out.println("notify...");
//                    this.countDownLatch.notify();
//                }
//            }

        }catch ( Exception ex ){
            Thread.currentThread().interrupt();
            log.error("线程发生异常了！",ex);
        }finally {
            this.countDownLatch.countDown();
            System.out.println("未完成线程数："+this.countDownLatch.getCount());
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

    private void throwException() throws Exception{
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println("随机数："+i);
        if( i<5 ){
            throw new Exception();
        }
    }




}
