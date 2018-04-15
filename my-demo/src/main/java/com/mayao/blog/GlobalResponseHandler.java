package com.mayao.blog;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * function ：返回值拦截自定义
 * @author ：mayao
 * @date ：2018/4/14
 */
@Slf4j
@ControllerAdvice(basePackages = { "com.mayao.ctrls"})
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //判断支持的类型，因为我们定义的BaseResponse 里面的data可能是任何类型，这里就不判断统一放过
        //如果你想对执行的返回体进行操作，可将上方的Object换成你自己的类型
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        //注解@Slf4j所带，属于非常推荐的lombok项目，可以去看看我的idea插件去了解一下
        log.info("请求返回数据类型class={}",body.getClass().getName());
        BaseResponse result=null;
        //兼容原来的接口返回
        if(body instanceof BaseResponse){
            result=(BaseResponse)body;
        }else{
            result=new BaseResponse();
            if(null!=body&&!"".equals(body)){
                result.setCode(200);
                result.setMessage("成功");
                result.setData(body);
            }
        }
        if(log.isDebugEnabled()){
            log.debug("响应参数：{} ", JSON.toJSONString(result));
        }
        return result;
    }
}
