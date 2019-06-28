package com.yuu.ymall.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员实体
 */
@Data
public class TbMember implements Serializable {
    /**
     * 会员 id
     */
    private Long id;

    /**
     * 会员用户名
     */
    private String username;

    /**
     * 会员密码
     */
    private String password;

    /**
     * 会员手机号
     */
    private String phone;

    /**
     * 会员邮箱
     */
    private String email;

    /**
     * 会员性别
     */
    private String sex;

    /**
     * 会员状态
     */
    private Integer state;

    /**
     * 会员头像
     */
    private String file;

    /**
     * 会员描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 前台登录 Token
     */
    private String token;
}