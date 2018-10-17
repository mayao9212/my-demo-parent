package com.mayao.myspringmvc.ctrl;

import com.mayao.myspringmvc.async.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/7/31
 */
@Slf4j
@RestController
public class IndexCtrl {

    @Autowired
    private AsyncTask asyncTask;


    @RequestMapping("/index")
    public String index() throws Exception{
        long currentTimeMillis = System.currentTimeMillis();
//        asyncTask.task1();
//        asyncTask.task2();
//        asyncTask.task3();

        Future<String> task1 = asyncTask.task4();
        Future<String> task2 = asyncTask.task5();
        Future<String> task3 = asyncTask.task6();
        String result = null;
        for (;;) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                log.info("都完成了！！");
                break;
            }
            Thread.sleep(1000);
        }

        //异常测试
        Future<String> future = asyncTask.exceptionSync();
        log.info("异常捕捉返回：{}",future.get());


        asyncTask.exceptionSyncVoid();

        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
    }

    @Async
    public void task1() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task1任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }

    @Async
    public void task2() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task2任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }
    @Async
    public void task3() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task3任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }


}
