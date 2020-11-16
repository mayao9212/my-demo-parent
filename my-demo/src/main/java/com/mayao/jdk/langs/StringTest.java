package com.mayao.jdk.langs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: mayao
 * @date: 2018-12-27 16:48
 */
public class StringTest {

    @Test
    public void arrayTest(){
        String[] array = {"A","B","C"};
        Object stringObj = (Object)array;
        System.out.println(Arrays.toString(array).replace("[","").replace("]",""));
        System.out.println(array.toString());
        System.out.println(Arrays.toString(array).replace("[","").replace("]",""));

        System.out.println(array instanceof String[]);
        System.out.println(stringObj instanceof String[]);
        System.out.println(stringObj instanceof int[]);
        System.out.println(stringObj.getClass().getTypeName());

        int[] intArray = {1,2,3};
        System.out.println(Arrays.toString(intArray).replace("[","").replace("]",""));
        System.out.println(intArray instanceof int[]);

    }

    @Test
    public void testStrings()
    {
        String [] strs = {};
        int [] ints = {};
        Object [] objects = {};

        System.out.println(ints.getClass().isAssignableFrom(int [].class));
        System.out.println(strs.getClass().isAssignableFrom(String [].class));
        System.out.println(objects.getClass().isAssignableFrom(Object [].class));
    }



    @Test
    public void sqlSubTest(){

        String sql = "select count(1) from crowd_user where city like '%sh%'";

        sql = sql.substring(sql.indexOf("where")+5,sql.length());
        System.out.println("StringTest.sqlSubTest："+sql);
    }


    @Test
    public void sbTest(){

        StringBuilder sb = new StringBuilder();
        sb.append("select count(1) from crowd_user").append(" where city like '%sh%'");
        String sql = sb.toString();
        System.out.println("sql:  "+sql);

        //sb sb清空
        sb.delete(0,sb.length());
        sb.append("test").append("delete");
        String sql2 = sb.toString();
        System.out.println("sql2:  "+sql2);

    }

    @Test
    public void matches(){
        String a = "ProductManagement";
        Boolean boo = a.matches("[a-zA-Z]+");
        System.out.println(boo);
    }

    @Test
    public void jsonTest(){
        String configDetail = "[{\"name\":\"订单编号\",\"metaObjectId\":23,\"tableName\":\"ct_order\",\"metaAttributeId\":24,\"columnName\":\"order_no\",\"inputType\":\"input\"}]";
        List<ConfigDetail> configDetail1s = JSONObject.parseArray(configDetail,ConfigDetail.class);
        System.out.println(JSON.toJSONString(configDetail1s));
    }


    @Getter
    @Setter
    public static class ConfigDetail{
        /**
         * 配置的列名，页面显式使用
         */
        private String name;

        /**
         * 元数据id
         */
        private Integer metaObjectId;

        /**
         * 表名
         */
        private String tableName;

        /**
         * 元数据属性id
         */
        private Integer metaAttribute;

        /**
         * 物理列名
         */
        private String columnName;

        /**
         * input：文本搜索；select-下拉；date-日期范围
         */
        private String inputType;

        /**
         * 新增需要，是否为必填项(0-是；1-不是
         */
        private Integer isMust;

        /**
         * 字段长度，body新增字段需要
         */
        private Integer length;

    }



}
