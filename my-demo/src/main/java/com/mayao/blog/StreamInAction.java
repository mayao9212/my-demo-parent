package com.mayao.blog;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * function ：Lambda表达式，Stream流的demo
 * @author ：mayao
 * @date ：2018/4/15
 */
public class StreamInAction {

    private List<User> userList;
    private static String WOMAN = "女";
    private static String MAN = "男";

    @Before
    public void initData(){
        User user0 = new User(15,"男");
        User user1 = new User(17,"女");
        User user2 = new User(24,"男");
        User user3 = new User(25,"女");
        User user4 = new User(27,"男");
        User user5 = new User(30,"男");
        User user6 = new User(36,"女");
        User user7 = new User(39,"女");
        User user8 = new User(40,"男");
        User user9 = new User(40,"女");
        userList = Arrays.asList(user0,user1,user2,user3,user4,user5,user6,user7,user8,user9);
    }

//    流的操作
//    接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
//    Intermediate：
//      map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
//    Terminal：
//      forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
//    Short-circuiting：
//      anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
//    我们下面看一下 Stream 的比较典型用法。


    //filter-->map-->reduce  的过程


    /**
     * 一对一的映射
     */
    @Test
    public void map(){
        //字母转大写
        List<String> stringList = Arrays.asList("a","s","v","e","q");
        stringList = stringList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        //将每个用户的年龄加5岁
        userList = userList.stream()
                .map(user -> {
                    user.setAge(user.getAge()+5);
                    return user;
                }).collect(Collectors.toList());
        //平方数
        List<Integer> nums = Arrays.asList(1,2,3,4);
        nums = nums.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());
        /**
         * 不难发现，map都是一对一的映射操作，多行逻辑用大括号：{}
         */
    }

    /**
     * 一对多的映射
     */
    @Test
    public void flatMap(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1,2,3),
                Arrays.asList(3,4),
                Arrays.asList(5,6)
        );
        Stream<Integer> outputStream = inputStream
                .flatMap(subList -> subList.stream());
        List<Integer> list = outputStream.collect(Collectors.toList());
        System.out.println(list);
        //不会去重
        /**
         * flatMap 把 input Stream 中的层级结构扁平化，将底层元素抽出来放到一起
         * 最终： output Stream 中里面已经没有List了，都是直接的数字
         */
    }

    /**
     * 过滤操作
     */
    public void filter(){
        //过滤性别为女的用户
        userList = userList.stream()
                .filter(user -> user.getSex().equals("女"))
                .collect(Collectors.toList());
        //配合map使用，年龄重返18岁
        userList = userList.stream()
                .filter(user -> user.getSex().equals("女"))
                .map(user -> {
                    user.setAge(18);
                    return user;
                })
                .collect(Collectors.toList());
        /**
         * 发现没有，流--》数据规则--》数据处理--》结果
         * 其实规则也是数据处理
         * 本质是：流数据--》数据处理--》结果
         */
    }

    /**
     * 循环
     */
    public void foreach(){
        //循环打印男性的年龄
        userList.stream()
                .filter(user -> user.getSex().equals(MAN))
                .forEach(user -> System.out.println(user.getAge()));
//                .forEach(user -> System.out.println(user.getSex()));
        /**
         * foreach 是 terminal（终端）操作，一个流不能重复多次使用。不能在后面再接一个 foreach,比如：
         * 但是在 intermediate（中间）操作，peek操作可以替代
         */

        userList.stream()
                .filter(user -> user.getAge()>18)
                .peek(user -> System.out.println(user.getAge()))
                .filter(user -> user.getSex().equals(WOMAN))
                .peek(user -> System.out.println(user.getSex()))
                .collect(Collectors.toList());
    }











}
