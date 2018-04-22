package com.mayao.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * function ：
 * @author ：mayao
 * @date ：2018/4/14
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse<T> {

    private int code;
    private String message;
    private T data;

}
