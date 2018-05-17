package com.mayao.jdk.thread.latch;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/17
 */
@Slf4j
public class CountDownLatchTest {

    static class Worker implements Runnable {
        private final CountDownLatch doneSignal;
        private final int            i;

        public Worker(CountDownLatch doneSignal, int i) {
            this.doneSignal = doneSignal;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(getRandomSeconds());
                System.out.println("Counting down："+ Thread.currentThread().getName());
                System.out.println(doneSignal.getCount());
            }catch ( InterruptedException ex){
                log.error("线程异常",ex);
            }finally {
                doneSignal.countDown();
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

    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        CountDownLatch countDownLatch = new CountDownLatch(N);
        ExecutorService executor = Executors.newFixedThreadPool(N);
        for (int i = 0; i < N; i++) {
            executor.execute(new Worker(countDownLatch, i));
        }
        System.out.println("线程开始等待");
        countDownLatch.await();
        System.out.println("over");
        executor.shutdown();
    }
}
