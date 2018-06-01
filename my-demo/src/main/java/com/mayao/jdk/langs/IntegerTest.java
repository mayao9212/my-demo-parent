package com.mayao.jdk.langs;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/17
 */
public class IntegerTest {

    @Test
    public void cache(){
        Integer a=1000,b=1000;
        System.out.println(a==b);
        System.out.println(a.equals(b));
        Integer c=100,d=100;
        System.out.println(c==d);
        /**
         *
         * 现在你可能会问，为什么这里需要缓存？

         合乎逻辑的理由是，在此范围内的“小”整数使用率比大整数要高，因此，使用相同的底层对象是有价值的，可以减少潜在的内存占用。

         然而，通过反射API你会误用此功能。
         *
         *     public static Integer valueOf(int i) {
                 if (i >= IntegerCache.low && i <= IntegerCache.high)
                 return IntegerCache.cache[i + (-IntegerCache.low)];
                 return new Integer(i);
                }
         */
    }

    /**
     * Integer反射测试
     */
    @Test
    public void reflectTest() throws NoSuchFieldException,IllegalAccessException{

        Class cache = Integer.class.getDeclaredClasses()[0];
        Field myCache = cache.getDeclaredField("cache");
        myCache.setAccessible(true);
        Integer[] newCache = (Integer[]) myCache.get(cache);

        //这里将缓存内的值，5给了4.。。
        newCache[132] = newCache[133];
        int a=2;
        int b=a+a;
        System.out.printf("%d + %d = %d",a,a,b);

    }




}
