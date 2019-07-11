package com.yuu.ymall.web.api.dto;

import java.io.Serializable;

/**
 * 头像上传接受请求对象
 *
 * @author by Yuu
 * @classname UploadImg
 * @date 2019/7/8 13:48
 */
public class UploadImg implements Serializable {

    /**
     * 会员 id
     */
    private Long userId;

    /**
     * 头像 base64 数据
     */
    private String imgData;

    /**
     * 会员缓存 token
     */
    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
