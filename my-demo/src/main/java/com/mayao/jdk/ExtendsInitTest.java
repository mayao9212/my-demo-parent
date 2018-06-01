package com.mayao.jdk;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/29
 */
public class ExtendsInitTest extends SecondExtend {

    public ExtendsInitTest() {
        System.out.println("最后一个继承");
    }

    public static void main(String[] args) {
        System.out.println("开始初始化。。");
        ExtendsInitTest extendsInitTest = new ExtendsInitTest();
        System.out.println("初始化完成。。");
    }

    /**
     * 继承的初始化是由内到外的扩散！！
     *
     * 清除时，顺序与生成顺序相反
     *
     */



}


class FirstExtend{
    public FirstExtend() {
        System.out.println("第一个继承");
    }
}

class SecondExtend extends FirstExtend{
    public SecondExtend() {
        System.out.println("第二个继承");
    }
}
