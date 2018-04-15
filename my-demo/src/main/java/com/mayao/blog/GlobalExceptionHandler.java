package com.mayao.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * function ：异常全局统一处理
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/4/14
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse jsonErrorHandler(HttpServletRequest request, Exception e) throws Exception{
        BaseResponse response = new BaseResponse();
        if( e instanceof BusinessException ){
            response.setCode(((BusinessException)e).getCode());
            response.setMessage(e.getMessage());
        }else{
            log.error(e.getMessage(),e);
            response.setCode(ResponseEnum.SYSTEM_ERROR.getCode());
            response.setMessage(ResponseEnum.SYSTEM_ERROR.getMessage());
        }
        return response;
    }
}
