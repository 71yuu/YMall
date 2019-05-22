package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 */
@Data
@EqualsAndHashCode
public class TbUser extends BaseEntity {
    /**
     * 用户 id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户状态
     */
    private Integer state;

    /**
     * 用户描述
     */
    private String description;

    /**
     * 用户对应的角色 id
     */
    private Integer roleId;

    /**
     * 用户头像
     */
    private String file;

}