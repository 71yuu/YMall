package com.yuu.ymall.web.api.dto;

import java.io.Serializable;

/**
 * 接受会员操作请求
 *
 * @author by Yuu
 * @classname Member
 * @date 2019/7/8 16:08
 */
public class Member implements Serializable {

    /**
     * 会员 id
     */
    private Long userId;

    /**
     * 会员缓存 token
     */
    private String token;

    /**
     * 会员头像 base64
     */
    private String imgData;

    /**
     * 会员名称
     */
    private String username;

    /**
     * 会员手机号
     */
    private String phone;

    /**
     * 会员密码
     */
    private String password;

    /**
     * 会员邮箱
     */
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
