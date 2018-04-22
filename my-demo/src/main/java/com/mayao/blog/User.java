package com.mayao.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

/**
 * function ：用户示例
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/4/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {

    private int index;

    private String userName;

    private int age;

    private String sex;

    public User(String userName, int age, String sex) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }

    public User(int index, String userName, int age) {
        this.index = index;
        this.userName = userName;
        this.age = age;
    }
}
