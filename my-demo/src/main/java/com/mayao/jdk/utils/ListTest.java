package com.mayao.jdk.utils;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/23
 */
public class ListTest {

    /**
     * 业务场景：角色关联用户，区分哪些新增，哪些删除
     */
    @Test
    public void newOldTest(){
        String[] userIds = new String[]{"1","2","3","4","5","6","7"};
        String[] oldUserIds = new String[]{"12","22","32","42","5","6","7"};

        //转换成集合
        List<String> userIdList = new ArrayList<>(Arrays.asList(userIds));
        List<String> oldUserIdList = new ArrayList<>(Arrays.asList(oldUserIds));

        if(CollectionUtils.isEmpty(userIdList)){
            //全部删除
        }

        long jdkStart2 = System.currentTimeMillis();
        //需要删除的
        List<String> removes = oldUserIdList.stream()
                .filter(c->!userIdList.contains(c))
                .collect(Collectors.toList());
        System.out.println("lamda需要删除："+removes);

        //删除操作。。

        //需要新增的
        List<String> inserts = userIdList.stream()
                .filter(c->!oldUserIdList.contains(c))
                .collect(Collectors.toList());
        System.out.println("lamda需要新增："+inserts);
        System.out.println("jdk 耗时："+(System.currentTimeMillis()-jdkStart2));

        long jdkStart = System.currentTimeMillis();
        //取出交集
        List<String> userIdList2 = new ArrayList<>(Arrays.asList(userIds));
        userIdList2.retainAll(oldUserIdList);
        System.out.println("交集："+userIdList2);

        //jdk方法
        oldUserIdList.removeAll(userIdList2);
        System.out.println("jdk需要删除："+oldUserIdList);
        //需要新增’
        userIdList.removeAll(userIdList2);
        System.out.println("jdk需要新增："+userIdList);
        System.out.println("jdk 耗时："+(System.currentTimeMillis()-jdkStart));

    }

    @Test
    public void removeTest(){
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");

        List<String> list2 = new ArrayList<>();
        list2.add("C");
        list2.add("B");

        // 2个集合的并集
        list1.removeAll(list2);
        System.out.println("差集:" + list1);
    }

    @Test
    public void trimToSizeTest(){
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        /**
         * 初始化10容量数组，只用了三个，调用此方法，将七个空的去除，只保留三个位置
         */
        list1.trimToSize();
        System.out.println(list1);

        Object[] array = list1.toArray();
        System.out.println(array);

        Object[] objArr = list1.toArray(new String[]{"D","F"});
        System.out.println(objArr);

        list1.add("D");
        print(list1);
        list1.add(3,"F");
        print(list1);


    }


    public static void print(Object obj){
        System.out.println(obj);
    }

    @Test
    public void arrayTest(){
        String area = "814,815,816,";
        String[] array = area.split(",");
        for (int i = 0; i < array.length; i++) {
            print(array[i]+"--");
        }
    }

    @Test
    public void linkedListTest(){

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        print(linkedList.toString());

    }

}
