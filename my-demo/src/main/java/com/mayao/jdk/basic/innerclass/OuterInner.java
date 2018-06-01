package com.mayao.jdk.basic.innerclass;

/**
 * function ：内部类
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/1
 */
public class OuterInner {

    private String outName;

    private void outMethod(){
        System.out.println("outName:"+this.outName);
    }

    /**
     * 内部类
     */
    class Inner{
        private String innerName;

        public Inner(String innerName){
            this.innerName=innerName;
            outName = innerName;
            System.out.println("innerName = [" + innerName + "]");
        }

        public void printOutName(){
            System.out.println("outName:"+outName);
        }

        public void innerMethod(){
            System.out.println("innerName:"+this.innerName);
        }

        //引用外部对象
        public OuterInner outerInner(){
            return OuterInner.this;
        }

    }

    public Inner inner(String something){
        return new Inner(something);
    }

    public static void main(String[] args) {

        OuterInner outerInner = new OuterInner();
        //这种引用方式
        OuterInner.Inner inner = outerInner.inner("做一些事情");
        //可以直接访问外围类的元素
        inner.printOutName();

        //内部类引用外部对象，并调用外部方法；；
        OuterInner.Inner inner2 = outerInner.inner("this关键字");
        inner2.outerInner().outMethod();

        //new 关键字
        outerInner.new Inner("new 关键字");


    }

    /**
     * 内部类自动拥有对其外部类所有成员的访问权
     *
     * 在拥有外部对象之前，是不可能创建内部类对象的！  因为内部类暗暗的连接到创建它的外部类对象上面去了
     */


}
