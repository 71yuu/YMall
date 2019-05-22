package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 会员实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbMember extends BaseEntity {
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
     * 会员地址
     */
    private String address;

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
     * 会员积分
     */
    private Integer points;

    /**
     * 会员余额
     */
    private BigDecimal balance;

}