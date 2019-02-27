package com.mayao.laotian;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Description:
 * @author: mayao
 * @date: 2018-12-29 10:10
 */
public class JsonTest {

    public static void main(String[] args) {

        //元数据
        String json = "{\"code\":0,\"message\":\"OK\",\"data\":[{\"memberId\":227361592,\"accountType\":\"CM\",\"accountNo\":6101970551811228,\"balance\":1000,\"feeRate\":8500,\"status\":\"N\",\"payRank\":0,\"orderLimit\":100000,\"dailyLimit\":1000000}],\"success\":true}";

        //解析成对象
        Meta meta = JSON.parseObject(json,Meta.class);

        //拿到数据
        List<Meta.DataEntity> dataEntities =  meta.getData();

        //循环
        for (Meta.DataEntity entity :dataEntities) {
            System.out.println("memberId的值："+entity.getMemberId());
        }

    }


}
