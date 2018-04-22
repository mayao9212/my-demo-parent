package com.mayao.blog;

import lombok.Getter;

/**
 * function ：返回码枚举类
 * @author ：mayao 
 * @date ：2018/4/14
 */
@Getter
public enum ResponseEnum {

    //格式与构造参数保持一致
    SUCCESS(200,"成功"),
    SYSTEM_ERROR(500,"系统内部错误");

    /**
     * 枚举值不可变更，不需要set方法，直接是指final
     */
    private final int code;
    private final String message;

    /**
     * 构造函数代表本枚举的参数结构
     * @param code
     * @param message
     */
    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过code获取对应枚举信息，反之亦可
     * @param code
     * @return
     */
    public static String getMsgByCode(int code){
        for (ResponseEnum responseEnum:ResponseEnum.values()){
            if (responseEnum.getCode()==code) {
                return responseEnum.getMessage();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("直接获取code值："+ResponseEnum.SUCCESS.getCode());//200
        System.out.println("直接获取message值："+ResponseEnum.SUCCESS.getMessage());//成功
        System.out.println("通过code获取对应message："+ResponseEnum.getMsgByCode(200));//成功
    }

}
