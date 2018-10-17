package com.mayao.myspringmvc.async;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * function ：异步线程，异常处理的逻辑；
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/8/1
 */
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... objects) {

//        log.info("@Async异步处理有错误出现，method:{},objects:{},msg:{}",method.getName(), JSON.toJSONString(objects),ex.getMessage());

        log.info("Exception message - " + ex.getMessage());
        log.info("Method name - " + method.getName());
        for (Object param : objects) {
            log.info("Parameter value - " + param);
        }

        if (ex instanceof AsyncException) {
            AsyncException asyncException = (AsyncException) ex;
            log.info("asyncException:{}",asyncException.getErrorMessage());
        }

        log.info("Exception :");
        ex.printStackTrace();

    }
}
