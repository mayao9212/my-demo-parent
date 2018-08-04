package com.mayao.stream;

import com.mayao.blog.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.*;


/**
 * function ：Lambda表达式，Stream流的demo
 * @author ：mayao
 * @date ：2018/4/15
 */
@Slf4j
public class StreamInActionTest {

    private List<User> userList;
    private static String WOMAN = "女";
    private static String MAN = "男";

    @Before
    public void initData(){
        User user0 = new User("用户1",30,MAN);
        User user1 = new User("用户2",17,WOMAN);
        User user2 = new User("用户3",100,MAN);
        User user3 = new User("用户4",39,WOMAN);
        User user4 = new User("用户5",27,MAN);
        User user5 = new User("用户6",15,MAN);
        User user6 = new User("用户10",36,WOMAN);
        User user7 = new User("用户11",24,WOMAN);
        User user8 = new User("用户20",40,MAN);
        User user9 = new User("用户21",100,WOMAN);
        userList = Arrays.asList(user0,user1,user2,user3,user4,user5,user6,user7,user8,user9);
//        out.println(userList);
    }

    @Test
    public void testCount(){
        long count = userList.stream().mapToInt(User::getAge).sum();
        println(count);
    }

    @Test
    public void strAppend(){
        String str = userList.stream().map(User::getUserName).collect(Collectors.joining(",")).toString();
        println(str);
    }

    @Test
    public void test(){

        User user0 = new User("用户1",30,MAN);
        User user1 = new User("用户2",17,WOMAN);
        User user2 = new User("用户3",100,MAN);
        User user3 = new User("用户4",39,WOMAN);

        List<User> users = Arrays.asList(user0,user1,user2,user3);

        List<Integer> ages = Arrays.asList(30,17);

        users = users.stream()
                .filter(c->!ages.contains(c.getAge()))
                .collect(Collectors.toList());
        println(users);

        users.forEach(c->{
            println(c);
        });

    }



    public static void println(Object object){
        out.println(object);
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
    public void mapTest(){
        //字母转大写
        List<String> stringList = Arrays.asList("a","s","v","e","q");
        stringList = stringList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        //将每个用户的年龄加5岁，多行操作
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
        System.out.println("map 平方数："+nums);
        /**
         * 不难发现，map都是一对一的映射操作，多行逻辑用大括号：{}
         */
    }

    /**
     * 一对多的映射
     */
    @Test
    public void flatMapTest(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1,2,3),
                Arrays.asList(3,4),
                Arrays.asList(5,6)
        );
        Stream<Integer> outputStream = inputStream
                .flatMap(subList -> subList.stream());
        List<Integer> list = outputStream.collect(Collectors.toList());
        println(list);
        //不会去重
        /**
         * flatMap 把 input Stream 中的层级结构扁平化，将底层元素抽出来放到一起
         * 最终： output Stream 中里面已经没有List了，都是直接的数字
         */
    }

    /**
     * 过滤操作
     */
    @Test
    public void filterTest(){
        //过滤性别为女的用户
        userList = userList.stream()
                .filter(user -> user.getSex().equals(WOMAN))
                .collect(Collectors.toList());

        //配合map使用，年龄重返18岁
        userList = userList.stream()
                .filter(user -> user.getSex().equals(WOMAN))
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
    @Test
    public void foreachTest(){
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

    /**
     *
     */
    @Test
    public void findFirstTest(){

        Optional<User> userOptional = userList.stream()
                .findFirst();

        User user = userOptional.get();
        log.info("第一个用户：{}",user);

        //判断是否为空
        userOptional.isPresent();

        //下面是源码
//        public boolean isPresent() {
//            return value != null;
//        }
//        Optional.ofNullable(userOptional).map(int::getAge).orElse(-1);
        //打印true
        System.out.println(Optional.ofNullable(user).isPresent());

        Integer age = userList.stream()
                .map(User::getAge)
                .findFirst()
                .get();
        log.info("第一个年纪：{}",age);

    }

    /**
     * Optional相关用法认识
     *      下面有两个用例
     *   在更复杂的 if( x != null) 的情况中，使用Optional代码的可读性更好，而且它提供了的是编译时检查，
     *   能极大的见地NPE这种 RuntimeException 运行时异常对程序的影响，让程序员在更早的编译阶段处理
     *   空值的问题；；
     *   Stream 中 findAny，max/min，reduce 等都返回的是Optional值；
     *   IntStream.average() 返回 OptionalDouble 等等；；
     */
    @Test
    public void optionalTest(){
        String strA = "abcd",strB = null;
        //测试
        printTest(strA);
//        print(strB);
        getStrLengthTest(strA);

        //创建一个新的optional
        Optional optional = Optional.empty();
        //有值的optional
        Optional<User> userOptional = Optional.of(new User("这是一个新用户",28,MAN));
        //找不到年龄小于27
        User user27 = userOptional.filter(c->c.getAge()<27).orElse(new User("没有用户",-1,MAN));
        log.info(user27.toString());
    }

    private void printTest(String string){
        //判断并打印
        //Java 8  ifPresent  是否存在
        Optional.ofNullable(string).ifPresent(System.out::print);
        //多行语句,直接代替 null != string  这种判断了
        Optional.ofNullable(string).ifPresent(c ->{
            c = c.toUpperCase();
            log.info("optional多行语句操作：{}",c);
        });
        //pre java 8
        if( null != string){
            log.info("pre java8 print:{}",string);
        }

        //判断是否为存在，直接返回布尔类型
        boolean isNull = Optional.ofNullable(string).isPresent();
        log.info("是否存在：{}",isNull);

    }

    private void getStrLengthTest(String string){
        //获取字符串长度(不为null就返回字符串长度，为null就返回-1)
        int length = Optional.ofNullable(string).map(String::length).orElse(-1);
        log.info("java8 :{},长度：{}",string,length);

        //pre java8  三元表达式
        int length2 = null != string ? string.length() : -1;
        log.info("pre java8 :{},长度：{}",string,length2);
    }

    /**
     * 排序
     */
    @Test
    public void sorted(){
        //对用户组按照年龄排序，小到大
//        userList = userList.stream().sorted().collect(Collectors.toList());
//        直接排序，需要实现Comparable方法
//        log.info("直接排序：{}",userList);
        Set<Integer> set = Stream.of(2,5,1,7,9,5).sorted().collect(Collectors.toSet());
        log.info("Interger的排序：{}",set);

        userList = userList.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
        log.info("根据年龄排序结果：{}",userList);

        userList = userList.stream()
                .sorted(Comparator.comparing(User::getUserName))
                .collect(Collectors.toList());
        log.info("根据用户姓名排序结果：{}",userList);
    }

    /**
     * 聚合操作：
     *  sum  求和
     *  min 最小
     *  max 最大
     *  distinct 唯一
     *
     */
    @Test
    public void sumMinMaxDistinct(){

        //求和
        int sum = userList.stream().
                mapToInt(User::getAge)
                .sum();
        log.info("年龄总和：{}",sum);
        //最小
        OptionalInt optionalInt = userList.stream()
                .mapToInt(User::getAge)
                .min();
        int min = optionalInt
                .getAsInt();
        log.info("年龄最小:{}",min);
        //年龄最大
        int max = userList.stream().mapToInt(User::getAge)
                .max()
                .getAsInt();
        log.info("年龄最大：{}",max);
        //去重,获得性别
        List<String> sex = userList.stream()
                .map(User::getSex)
                .distinct()
                .collect(Collectors.toList());
        log.info("性别集合为:{}",sex);

    }


    /**
     *  这个方法主要是把Stream元素组合起来。
     *  它提供一个起始值-》规则操作-》结果组合起来
     *
     */
    @Test
    public void reduce(){

        //没有初始值，返回的值Optional对象
        int sum = IntStream.of(1,2,3,4,5).reduce((a,b) -> a+b).getAsInt();
        log.info("reduce sum 无初始值：{}",sum);//15
        //有初始值返回结果
        int sum2 = IntStream.of(1,2,3,4,5).reduce(100,(a,b) -> a+b);
        log.info("reduce sum2 有初始值：{}",sum2);//115
        //过滤，字符串拼接
        String reduceStrConcat = Stream.of("A","a","b","C","e","E")
                .filter(c -> c.compareTo("Z")>0)
                .reduce("",String::concat);
        log.info("reduce filter string concat :{}",reduceStrConcat);//abe

        //我们可以看下sum的源码。。发现也是reduce
//        @Override
//        public final int sum() {
//            return reduce(0, Integer::sum);
//        }
        /**
         * 所以，min,max,average 都是特殊的reduce操作
         */
    }

    /**
     * limit：返回stream前面的几个元素
     * skip：扔掉前面几个元素
     */
    @Test
    public void limitSkip(){

        userList = userList.stream()
                .limit(5L)
                .collect(Collectors.toList());
        println("limit 前面的五个元素："+userList);
        userList = userList.stream()
                .skip(2L)
                .collect(Collectors.toList());
        println("skip 五个元素再去掉前面两个："+userList);

    }

    /**
     * allMatch：所有符合条件的值
     * anyMatch：Boolean值，一个符合传入的值就返回true
     * noneMatch：没有一个符合的值就返回true
     *
     */
    @Test
    public void match(){
        //都满足条件返回true
        boolean allMatch = userList.stream().allMatch(c->c.getAge()<101);
        println("所有用户都小于101岁："+allMatch);//true  最大才100
        //有一个满足就返回true
        boolean anyMatch =userList.stream().anyMatch(c->c.getAge()<20);
        println("至少有一个用户小于20岁："+anyMatch);//true  里面有个15岁的
        //都不满足就返回true
        boolean noneMatch = userList.stream().noneMatch(c->c.getSex().equals(MAN));
        println("用户都是男的："+noneMatch);//false  里面还是有妹纸的



        /**
         * 延伸：
         *      anyMatch 让我想起了 List(T t).contains(T t);
         *      //这是源码
         *     public boolean contains(Object o) {
                    return indexOf(o) >= 0;
                }
                //indexOf
                 public int indexOf(Object o) {
                     if (o == null) {
                     for (int i = 0; i < size; i++)
                        if (elementData[i]==null)
                        return i;
                     } else {
                     for (int i = 0; i < size; i++)
                        if (o.equals(elementData[i]))
                        return i;
                     }
                     return -1;
                 }

         anyMatch..源码暂时看的有点晕，有兴趣的可以去看看

         *
         */

    }

    /**
     * used in lambda shoudle be final or efftively final
     * 语法糖解决
     *
     */
    @Test
    public void shoudbleFinalTest(){

        List<String> strings = new ArrayList<>();
        strings.add(MAN);

        if( 1==strings.size() ){
            List<String> strings2 = Arrays.asList(WOMAN);
            strings.addAll(strings2);
        }

        userList.forEach(c->{
            println(strings);
        });

    }

}
