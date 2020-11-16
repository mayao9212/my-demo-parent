package com.mayao.jdk.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/7/29
 */
public class HashMapTest{


    @Test
    public void transferTest(){

        Map<String,String> hashMap = new HashMap();
        hashMap.put("condition10","crowd_302_condition_1_page_49_0_no");
        hashMap.put("condition13","crowd_302_condition_1_page_62_1_no");
        hashMap.put("condition11","crowd_302_condition_1_page_62_1_no");
        hashMap.put("condition12","crowd_302_condition_1_page_62_1_no");
        //
        for(String key:hashMap.keySet()){
            System.out.println("HashMapTest.transferTest----"+key);
        }

        System.out.println("------------------");

        Map<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("condition10","crowd_302_condition_1_page_49_0_no");
        linkedHashMap.put("condition13","crowd_302_condition_1_page_62_1_no");
        linkedHashMap.put("condition11","crowd_302_condition_1_page_62_1_no");
        linkedHashMap.put("condition12","crowd_302_condition_1_page_62_1_no");
        //
        for(String key:linkedHashMap.keySet()){
            System.out.println("HashMapTest.transferTest----"+key);
        }




    }






}
