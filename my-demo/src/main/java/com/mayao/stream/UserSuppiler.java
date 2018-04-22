package com.mayao.stream;

import com.mayao.blog.User;

import java.util.Random;
import java.util.function.Supplier;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/4/22
 */
public class UserSuppiler implements Supplier<User> {

    private Random random = new Random(100);
    private int index;

    @Override
    public User get() {
        return new User(index++,"UserSuppiler"+index,random.nextInt(100));
    }
}
