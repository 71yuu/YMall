package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色对应的权限实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbRolePerm extends BaseEntity {
    /**
     * 角色对应的权限 id
     */
    private Integer id;

    /**
     * 角色 id
     */
    private Integer roleId;

    /**
     * 权限 id
     */
    private Integer permissionId;

}