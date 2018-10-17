package com.mayao.myspringmvc.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * function ：spring异步执行配置
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/8/1
 */
@Slf4j
@EnableAsync
@Configuration
public class SpringAsyncConfig implements AsyncConfigurer {

    /**
     * 核心线程数,默认1
     */
    private int corePoolSize = 5;
    /**
     * 线程池最大线程数，默认3
     */
    private int maxPoolSize = 15;
    /**
     * 额外线程空状态生存时间，默认10分钟
     */
    private int keepAliveTime = 10;
    /**
     * 阻塞队列大小
     */
    private int queueSize = 500;


    @Bean
    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setThreadNamePrefix("AsyncExecutorThread-");
        executor.initialize(); //如果不初始化，导致找到不到执行器
        log.info("异步处理线程池创建成功");
        return executor;
    }

    @Bean
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        log.info("异步处理异常捕捉创建成功");
        return new AsyncExceptionHandler();
    }
}
