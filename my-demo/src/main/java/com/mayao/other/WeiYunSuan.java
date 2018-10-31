package com.mayao.other;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/29
 */
public class WeiYunSuan {

    @Test
    public void leftTest(){
        //1073741824
        System.out.println(1 << 30);
        //-2147483648
        System.out.println(2 << 30);
        //16
        System.out.println(1 << 4);
    }

    @Test
    public void ifNullTest(){

        Map<String,Boolean> map = new HashMap<>();
        map.put("a",true);

        if( map.get("b") ){
            System.out.printf("过");
        }else {
            System.out.printf("不过");
        }

    }

}
