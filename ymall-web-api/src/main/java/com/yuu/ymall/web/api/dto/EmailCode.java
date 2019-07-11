package com.yuu.ymall.web.api.dto;

/**
 * 邮箱随机验证码
 *
 * @author by Yuu
 * @classname EmailCode
 * @date 2019/6/28 19:40
 */
public class EmailCode {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 随机验证码
     */
    private String code;

    public EmailCode(String code) {
        this.code = code;
    }

    public EmailCode(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
