package com.mayao.jdk.compartor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/1/31
 */
public class GroupTest {


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Apple{

        private String color;
        private int weight;

    }

    /**
     * 分组之后的比较
     * @param collection       目标集合
     * @param c                一个比较器
     * @param <T>              未知类型
     * @return
     */
    public static <T> List<List<T>> divider (Collection<T> collection,Comparator<? super T> c){

        List<List<T>> result = new ArrayList<List<T>>();

        collection.forEach(t->{
            boolean isSameGroup = false;

            //根据已知的比较器判断是否为同一组
            for (int j = 0; j < result.size(); j++) {
                if (c.compare(t, result.get(j).get(0)) == 0) {
                    isSameGroup = true;
                    result.get(j).add(t);
                    break;
                }
            }

            //
            if (!isSameGroup) {
                // 创建
                List<T> innerList = new ArrayList<T>();
                result.add(innerList);
                innerList.add(t);
            }

        });

        return result;
    }



}
