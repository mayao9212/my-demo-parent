package com.mayao.blog;

import lombok.Getter;

/**
 * function ：自定义业务异常
 * @author ：mayao
 * @date ：2018/4/14
 */
@Getter
public class BusinessException extends Exception {

    private int code;

    public BusinessException(int code,String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResponseEnum responseEnum){
        super(responseEnum.getMessage());
        this.code=responseEnum.getCode();
    }

    //直接构建系统内部错误
    public static BusinessException systemException(String message){
        return new BusinessException(ResponseEnum.SYSTEM_ERROR.getCode(),message);
    }

}
