package com.mayao.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * function ：信号灯，，测试,,一般结合线程池使用
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/8/4
 */
public class SemahoreDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(1,true);

        for (int i=0;i<10;i++){
            Thread thread = new Thread(()->{

                try {
                    semaphore.acquire();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("线程名："+Thread.currentThread().getName()+"进入，已有"+(3-semaphore.availablePermits())+"并发");

                try {
                    Thread.sleep((long)(Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程"+Thread.currentThread().getName()+"即将离开");

                semaphore.release();

                System.err.println("线程"+Thread.currentThread().getName()+"已经离开"+"当前并发数："+(3-semaphore.availablePermits()));

            });
            executorService.execute(thread);
        }

        executorService.shutdown();
    }
    /**
     * 1、Semaphore往往结合线程池使用，比如建立一个固定大小为10的线程池，最多线程并发数为10个，
     * 当你提交20个任务到线程池的时候，线程池会安排这10个线程优先接待20个任务中的10个，
     * 当优先安排的10个中有完成的会去接待剩下的10个任务中的某一个任务，直到执行完所有任务为止。
     * 但是如果此时引入了Semaphore对象，所传的值是5的时候，那么这线程池中10个线程只有5个能够并发执行，此时就做到了限量访问的作用。

     2、当Semaphore构造方法中传入的参数是1的时候，此时线程并发数最多是1个，即是线程安全的，
     这种方式也可以做到现场互斥。Java实现互斥线程同步有三种方式synchronized、lock 、单Semaphore
     */

}
