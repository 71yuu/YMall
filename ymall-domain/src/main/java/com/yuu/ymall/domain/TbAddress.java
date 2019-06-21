package com.yuu.ymall.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 地址实体
 */
@Data
public class TbAddress implements Serializable {
    /**
     * 地址 id
     */
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 电话
     */
    private String tel;

    /**
     * 详细地址
     */
    private String streetName;

    /**
     * 是否为默认地址
     */
    private Boolean isDefault;

    /**
     * 创建日期
     */
    private Date created;

    /**
     * 更新日期
     */
    private Date updated;
}