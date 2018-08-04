package com.mayao.jdk.thread.latch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * function ：手动创建线程池
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/4/11
 */
public class ThreadPool {
    private static final AtomicInteger atomicInteger = new AtomicInteger();
    /**
     * 核心线程数,默认1
     */
    private int corePoolSize = 3;
    /**
     * 线程池最大线程数，默认3
     */
    private int maxPoolSize = 10;
    /**
     * 额外线程空状态生存时间，默认10分钟
     */
    private int keepAliveTime = 10;
    /**
     * 阻塞队列大小
     */
    private int queueSize = 500;
    /**
     * 阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程
     */
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(this.queueSize);

    /**
     * 创建线程工厂
     */
    private static ThreadFactory threadFactory = ( (Runnable r) ->
            new Thread(r,"ThreadPool thread:"+atomicInteger.getAndIncrement()));

    /**
     * 获取线程池
     * @return
     */
    private ThreadPoolExecutor getPool(){
        return new ThreadPoolExecutor(this.corePoolSize,this.maxPoolSize,this.keepAliveTime, TimeUnit.MINUTES,workQueue,threadFactory);
    }

    private ThreadPool() {}

    /**
     * 延迟初始化占位（Holder）类模式：需要知道内部类的加载时机；
     * 内部类的加载：内部类（不论是静态内部类还是非静态内部类）都是在第一次使用时才会被加载。
     * 这个加载的过程是不会有多线程的问题的！
     * 类加载的时候有一种机制叫做缓存机制；第一次加载成功之后会被缓存起来；而且一般一个类不会加载多次;
     */
    private static class LazyHolder{
        private static final ThreadPoolExecutor THREAD_POOL = new ThreadPool().getPool();
    }
    public static final ThreadPoolExecutor pool(){
        return LazyHolder.THREAD_POOL;
    }
//    public static final ThreadPoolExecutor pool(int corePoolSize, int maxPoolSize, int keepAliveTime){
//        ThreadPoolExecutor pool = LazyHolder.threadPool;
//        pool.setCorePoolSize(corePoolSize);
//        pool.setMaximumPoolSize(maxPoolSize);
//        pool.setKeepAliveTime(keepAliveTime,TimeUnit.MINUTES);
//        return pool;
//    }










}
