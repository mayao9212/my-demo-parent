package com.mayao.blog;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @Description: 类，反射测试
 * @author: mayao
 * @date: 2018-10-08 23:05
 */
public class BeanInvokeTest {


    @Test
    public void invokeTest() throws Exception{
        String operator = "马尧";
        Date date= new Date();

        User user = new User();
        user.setAge(100);
        user.setUserName("mayao");
        user.setIndex(1);
        user.setSex("男");
        System.out.println(JSON.toJSONString(user));

        invoke(user,operator,date);

        System.out.println(JSON.toJSONString(user));
    }

    private static final String CREATE_TIME = "createTime";
    private static final String CREATE_USER = "createUser";
    private static final String UPDATE_TIME = "updateTime";
    private static final String UPDATE_USER = "updateUser";


    void invoke(Object object,String operator,Date date) throws NoSuchFieldException,IllegalAccessException{

        Class c = object.getClass();
        //创建时间
        Field createTime = c.getDeclaredField(CREATE_TIME);
        createTime.setAccessible(true);
        createTime.set(object,date);
        //创建用户
        Field createUser = c.getDeclaredField(CREATE_USER);
        createUser.setAccessible(true);
        createUser.set(object,operator);
        //更新时间
        Field updateTime = c.getDeclaredField(UPDATE_TIME);
        updateTime.setAccessible(true);
        updateTime.set(object,date);
        //更新用户
        Field updateUser = c.getDeclaredField(UPDATE_USER);
        updateUser.setAccessible(true);
        updateUser.set(object,operator);

    }



}
