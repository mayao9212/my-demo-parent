package com.mayao.async;

import lombok.Data;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/8/1
 */
@Data
public class AsyncException extends Exception {

    private int code;
    private String errorMessage;

    public AsyncException() {
    }

    public AsyncException( String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
