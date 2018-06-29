package com.mayao.jdk.reflects;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mayao.blog.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * function ：反射测试
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/26
 */
@Slf4j
public class ReflectTest {

    @Test
    public void fieldTest() throws IllegalAccessException,IllegalArgumentException{

        User user = new User(null,30,"男");
//        Field[] fields = user.getClass().getFields();
//        log.info("getFields()");
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            log.info(field.toString());
//            System.out.println(field.toString());
//        }

        log.info("getDeclaredFields()");
        Field[] fields2 = user.getClass().getDeclaredFields();
        for (int i = 0; i < fields2.length; i++) {
            Field field = fields2[i];
            field.setAccessible(true);
            String name = field.getName();
            log.info(name);
            //属性名
            Object obj = field.get(user);
            log.info(JSON.toJSONString(obj));
        }

        log.info("-----------------------------------");
        paramsIsEmpty(user, Arrays.asList("userName"));

    }


    /**
     * 有为空返回true
     * @param object            需要校验的实体类
     * @param noCheckFiledNames 需要忽视校验的字段名
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static boolean paramsIsEmpty(Object object, List<String> noCheckFiledNames) throws IllegalArgumentException, IllegalAccessException{
        boolean isEmpty = false;
        //所有属性名
        Field[] fields = object.getClass().getDeclaredFields();
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String fieldName = field.getName();
            //开始校验,
            if( !hasAny(fieldName,noCheckFiledNames) ){
                //捕捉异常
                Object fieldValue;
                try{
                    fieldValue = field.get(object);
                }catch (Exception e){
                    throw new RuntimeException("reflect非法参数异常，paramsIsEmpty方法失效",e);
                }
                if( null == fieldValue ){
                    log.info("该属性名对应值为null fieldName：{}",fieldName);
                    isEmpty = true;
                    break;
                }
                if( fieldValue instanceof String ){
                    isEmpty = "".equals(((String)fieldValue).trim())?true:false;
                    if("".equals(((String)fieldValue).trim())){
                        log.info("该属性名对应值为空fieldName：{}",fieldName);
                        isEmpty = true;
                        break;
                    }
                }
            }
        }
        if( !isEmpty ){
            log.info("entity params check is not empty..");
        }
        return isEmpty;
    }

    /**
     * 字符串是否有匹配
     * @param target
     * @param checks
     * @return
     */
    public static boolean hasAny(String target,List<String> checks){
        if( null==checks || checks.size()==0 ){
            log.info("list is empty ,return false..");
            return false;
        }
        Optional<String> optional = checks.stream()
                .filter(c->c.equals(target))
                .findAny();
        if( optional.isPresent() ){
            return true;
        }
        return false;

    }



}
