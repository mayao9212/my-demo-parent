package com.mayao.blog;

import java.util.List;

/**
 * function ：用户接口
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/4/14
 */
public interface IUserService {
    int addUser(User user) throws Exception;
    BaseResponse<List<User>> userList(User user);

}
