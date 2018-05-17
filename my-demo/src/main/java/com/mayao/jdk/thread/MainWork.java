package com.mayao.jdk.thread;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/17
 */
public class MainWork {

    public static void main(String[] args) {

        WorkerManage workerManage = new WorkerManage();
        workerManage.startWork();
        System.out.println("工作完成！！");

    }

}
