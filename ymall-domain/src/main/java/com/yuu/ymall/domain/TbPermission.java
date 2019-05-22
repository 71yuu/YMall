package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Shiro 验证实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbPermission extends BaseEntity {
    /**
     * 验证 id
     */
    private Integer id;

    /**
     * 验证名称
     */
    private String name;

    /**
     * 验证路径
     */
    private String permission;

}