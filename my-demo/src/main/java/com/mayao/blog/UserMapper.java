package com.mayao.blog;

import java.util.List;

/**
 * function ：
 * @author ：mayao
 * @date ：2018/4/14
 */
public interface UserMapper {
    int addUser(User user);
    List<User> userList(User user);
    int updateUserInfo(User user);
    int countByName(String userName);
}
