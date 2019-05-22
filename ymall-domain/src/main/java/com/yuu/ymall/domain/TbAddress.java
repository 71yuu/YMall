package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地址实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbAddress extends BaseEntity {
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

}