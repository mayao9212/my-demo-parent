package com.mayao.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * function ：用户示例
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/4/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private String userName;

    private String email;

    private int age;

    private String sex;

    public User(int age, String sex) {
        this.age = age;
        this.sex = sex;
    }
}
