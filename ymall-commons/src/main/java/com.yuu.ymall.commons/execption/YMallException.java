package com.yuu.ymall.commons.execption;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常类
 *
 * @Classname YMallException
 * @Date 2019/5/12 22:01
 * @Created by Yuu
 */
@Getter
@Setter
public class YMallException extends RuntimeException {

    /**
     * 异常消息
     */
    private String msg;

    public YMallException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
