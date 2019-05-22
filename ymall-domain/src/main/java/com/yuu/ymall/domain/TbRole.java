package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Shiro 角色实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbRole extends BaseEntity {
    /**
     * 角色 id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

}