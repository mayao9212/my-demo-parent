package com.mayao.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * function ：用户控制器
 * @author ：mayao 
 * @date ：2018/4/14
 */
@RestController
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/add")
    public int addUser(User user) throws Exception{
        return iUserService.addUser(user);
    }

    @PostMapping("/list")
    public BaseResponse<List<User>> userList(User user){
        return iUserService.userList(user);
    }

}
