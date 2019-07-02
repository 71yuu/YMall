package com.yuu.ymall.web.api.dto;

import java.io.Serializable;

/**
 * 会员接受登录信息类
 *
 * @author by Yuu
 * @classname MemberLogin
 * @date 2019/6/27 9:42
 */
public class MemberLogin implements Serializable {

    /**
     * 账号：手机号或邮箱
     */
    private String account;

    /**
     * 邮箱
     */
    private String password;

    /**
     * 是否自动登录，否 账号信息存 session， 是 账号信息存 Redis Session
     */
    private Boolean auto;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }
}
