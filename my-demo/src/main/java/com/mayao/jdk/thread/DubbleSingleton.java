package com.mayao.jdk.thread;

/**
 * function ：双重单列检查,,双重校验锁,双重检查 Double-Check
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/7/4
 */
public class DubbleSingleton {

    /**
     *   volatile关键字的一个作用是禁止指令重排，把instance声明为volatile之后，
     * 对它的写操作就会有一个内存屏障，这样，在它的赋值完成之前，就不用会调用读操作。
     *
     *   注意：volatile阻止的不是singleton = new Singleton()这句话内部[1-2-3]的指令重排，
     * 而是保证了在一个写操作（[1-2-3]）完成之前，不会调用读操作（if (instance == null)）。
     */
    //加上volatile 关键字
    private volatile static DubbleSingleton dubbleSingleton;

    private DubbleSingleton(){}

    public static DubbleSingleton getInstance(){
        System.out.println("进");
        if( null == dubbleSingleton){
            try{
                System.out.println("等");
                Thread.sleep(2000L);
            }catch (InterruptedException e){

            }
            synchronized (DubbleSingleton.class){
                if(null == dubbleSingleton){
                    dubbleSingleton = new DubbleSingleton();
                }
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+dubbleSingleton);
        return dubbleSingleton;
    }

    public static void main(String[] args) {
//        int i = 10;
//        while (i>0){
//            i--;
//            new Thread(()->{
//                DubbleSingleton singleton = DubbleSingleton.getInstance();
//                System.out.println(singleton);
//            }).start();
//        }

        System.out.println("-------------------------------");

        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                DubbleSingleton.getInstance();
            }
        },"thead1");

        Thread thread2 = new Thread(new Runnable() {
            @Override public void run() {
                DubbleSingleton.getInstance();
            }
        },"thead2");


        thread.start();
        thread2.start();
    }

    /**
     * 第二种写法，静态内部类,,
     * 它利用了ClassLoader来保证了同步，同时又能让开发者控制类加载的时机。
     * 从内部看是一个饿汉式的单例，但是从外部看来，又的确是懒汉式的实现
     *
     */

    private static class SingletonHolder{
        private static final DubbleSingleton INSTANCE = new DubbleSingleton();
    }

    public static final DubbleSingleton getInstances(){
        return SingletonHolder.INSTANCE;
    }



}
