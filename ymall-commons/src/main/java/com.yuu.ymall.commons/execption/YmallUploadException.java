package com.yuu.ymall.commons.execption;

/**
 * @Classname YMallUploadException
 * @Date 2019/5/24 20:53
 * @Created by Yuu
 */
public class YmallUploadException extends RuntimeException {

    private String msg;

    public YmallUploadException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
