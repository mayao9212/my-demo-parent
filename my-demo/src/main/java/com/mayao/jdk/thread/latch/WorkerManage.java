package com.mayao.jdk.thread.latch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/16
 */
@Slf4j
public class WorkerManage {

    private CountDownLatch countDownLatch;

    private static final int NUMBER_OF_TASKS = 8;

    public WorkerManage(){
        this.countDownLatch = new CountDownLatch(NUMBER_OF_TASKS);
    }

    /**
     * 开始工作
     */
    public void startWork(){

        //推荐手动创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_TASKS);

        for (int i=0;i<NUMBER_OF_TASKS;i++){
            Worker worker = new Worker(this.countDownLatch);
            executorService.execute(worker);
        }
        executorService.shutdown();
    }

    /**
     * 结束工作
     */
    public void finishWork(){
//        synchronized (this.countDownLatch){
//            try {
//                System.out.println("线程开始等待。。");
//                this.countDownLatch.wait();
//                System.out.println("所有线程完成。。");
//            }catch (Exception e){
//                log.error("结束工作异常",e);
//            }
//        }

        //是await()方法,不是wait()方法

        try {
            System.out.println("线程开始等待。。");
            this.countDownLatch.await();
            System.out.println("所有线程完成。。");
        }catch (Exception e){
            log.error("结束工作异常",e);
        }

    }

    public static void main(String[] args) {
        WorkerManage workerManage = new WorkerManage();
        System.out.println("开始工作。。");
        workerManage.startWork();
        System.out.println("所有线程都开始跑了。。");
        workerManage.finishWork();
        System.out.println("工作完成。。");
    }

}
