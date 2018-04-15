package com.mayao.blog;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * function ：用户接口实现
 * @author ：mayao
 * @date ：2018/4/14
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) throws Exception{
        //如果名字为空，直接抛出异常
        if(StringUtils.isEmpty(user.getUserName())){
            throw new BusinessException(40001,"必要参数缺失");
        }
        return userMapper.addUser(user);
    }

    @Override
    public BaseResponse<List<User>> userList(User user) {
        BaseResponse<List<User>> baseResponse = new BaseResponse<>();
        baseResponse.setCode(200);
        baseResponse.setData(userMapper.userList(user));
        baseResponse.setMessage("成功");
        return baseResponse;
    }
}
