package com.mayao.jdk.basic.innerclass;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/1
 */
public class InnerTest extends Inner {


//    public InnerInterface get(){
    //        Inner inner = new Inner();
    //        return inner.new ProInner();
    //    }

    public static void main(String[] args) {

        InnerTest  innerTest = new InnerTest();

        InnerInterface innerInterface = innerTest.get();
        innerInterface.move();
        innerInterface.run();


    }
}
