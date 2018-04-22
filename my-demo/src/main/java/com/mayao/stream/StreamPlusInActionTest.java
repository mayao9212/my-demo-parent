package com.mayao.stream;

import com.mayao.blog.User;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.mayao.stream.StreamInActionTest.*;
import static java.lang.System.out;

/**
 * function ：Stream 的进阶用法
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/4/22
 */
public class StreamPlusInActionTest {

    private List<User> userList;
    private static String WOMAN = "女";
    private static String MAN = "男";

    @Before
    public void initData(){
        User user0 = new User("用户1",30,MAN);
        User user1 = new User("用户2",17,WOMAN);
        User user2 = new User("用户3",100,MAN);
        User user3 = new User("用户4",39,WOMAN);
        User user4 = new User("用户5",28,MAN);
        User user5 = new User("用户6",15,MAN);
        User user6 = new User("用户10",36,WOMAN);
        User user7 = new User("用户11",24,WOMAN);
        User user8 = new User("用户20",40,MAN);
        User user9 = new User("用户21",100,WOMAN);
        userList = Arrays.asList(user0,user1,user2,user3,user4,user5,user6,user7,user8,user9);
        out.println(userList);
    }

    /**
     * Integer
     */
    @Test
    public void integeSupplier(){
        Random random = new Random();
        Supplier<Integer> integerSupplier = random::nextInt;
        Stream.generate(integerSupplier)
                .limit(10)
                .forEach(System.out::println);
        // other way
        println("另一种方式....");
        IntStream.generate(() -> (int) (System.nanoTime() % 100))
                .limit(10)
                .forEach(System.out::println);

    }

    /**
     * 自己生成流
     */
    @Test
    public void userSupplier(){

        Stream.generate(new UserSuppiler())
                .limit(10)
                .forEach(c->println("姓名："+c.getUserName()+" 年龄："+c.getAge()));

    }

    /**
     * iterate  迭代器
     */
    @Test
    public void iterate(){
        Stream.iterate(0,n -> n +3)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * 按照某个属性分组，这个挺有用的
     *      Map<String,List<User>>  看分组条件和数据源
     */
    @Test
    public void groupBy(){

        //将不同性别的用户，分组开来
        Map<String,List<User>> userMap =userList.stream().
                collect(Collectors.groupingBy(User::getSex));

        Iterator iterator = userMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,List<User>> users = (Map.Entry)iterator.next();
            println("性别："+users.getKey()+" 用户集合："+users.getValue());
        }

    }

    /**
     * 多条件分组
     */
    @Test
    public void groupByTest2(){

        User user0 = new User("用户一组",30,MAN);
        User user1 = new User("用户二组",17,MAN);
        User user4 = new User("用户二组",19,MAN);
        User user2 = new User("用户三组",56,WOMAN);
        User user3 = new User("用户三组",39,WOMAN);
        List<User> users = Arrays.asList(user0,user1,user2,user3,user4);
        //想通过用户名+性别分组
        Map<String,Map<String,List<User>>> userMap = users.stream().
                collect(Collectors.groupingBy(User::getUserName
                        ,Collectors.groupingBy(User::getSex)));

        println("分组后的大小："+ userMap.size());

        userMap.forEach((k,v)->{
            println("当前组：" + k);
            v.forEach((k1,v1)->{
                println("里面一层key：" + k1);
                println("里面一层value：" + v1);
            });
        });
    }




    /**
     * 某个属性有条件的分类，也很有用
     */
    @Test
    public void partitioningBy(){

        //根据成年，未成年分组
        Map<Boolean,List<User>> userMaps = userList.stream()
                .collect(Collectors.partitioningBy(c -> c.getAge()<18));

        Iterator iterator = userMaps.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Boolean,List<User>> users = (Map.Entry)iterator.next();
            println("是否成年："+users.getKey()+" 用户集合大小："+users.getValue().size());
        }

//        是否成年：false 用户集合大小：8
//        是否成年：true 用户集合大小：2

    }




}
