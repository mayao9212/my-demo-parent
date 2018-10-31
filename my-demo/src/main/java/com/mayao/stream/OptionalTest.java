package com.mayao.stream;

import com.mayao.blog.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/5/30
 */
public class OptionalTest {

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
        userList = Arrays.asList(user0,user1,user2,user3,user4,user5,user6,user7,user8);
        //        out.println(userList);
    }

    @Test
    public void nullTest(){
        String a = null;
        String b = "1234";
        Optional optional = Optional.ofNullable(a);
        optional.orElse(b);
        System.out.println(optional.get());
    }

    @Test
    public void ofNullTest() throws Exception{
        Optional<User> optional = userList.stream()
                .filter(c->100==c.getAge())
                .findAny();

        optional.orElseThrow(()->new Exception("空指针"));

        System.out.println(optional);
//        User user = Optional.ofNullable(optional.get()).orElseThrow(()->new Exception("空指针"));

//        System.out.println(user);

        User user1 = optional.orElseThrow(()->new Exception("空指针"));
        System.out.println(user1);





    }
}
