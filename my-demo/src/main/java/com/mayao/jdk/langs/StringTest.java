package com.mayao.jdk.langs;

import org.junit.Test;

/**
 * @Description:
 * @author: mayao
 * @date: 2018-12-27 16:48
 */
public class StringTest {


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


}
