package com.mayao.jdk.reflects;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @Description:
 * @author: mayao
 * @date: 2019-11-15 10:31
 */
@Getter
@Setter
public class EntityInfo {

    private String javaName;

    private Map<String,String> columnTypeAndNameMap;

}
