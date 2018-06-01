package com.mayao.jdk.basic.innerclass;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/1
 */
public class Inner {


    protected class ProInner implements InnerInterface{
        @Override
        public void move() {
            System.out.println("开始移动了。。");
        }

        @Override
        public void run() {
            System.out.println("开始奔跑了。。");
        }


        private String name;
    }


    public InnerInterface get(){
        return new ProInner();
    }

    public static void main(String[] args) {

        Inner inner = new Inner();
        Inner.ProInner proInner = (Inner.ProInner)inner.get();
        String ss = proInner.name;


    }


}
