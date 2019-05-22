package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Shiro 请求路径对应的权限
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbShiroFilter extends BaseEntity {
    /**
     * id
     */
    private Integer id;

    /**
     * 请求路径名称
     */
    private String name;

    /**
     * 需要的权限
     */
    private String perms;

    /**
     * 排序值
     */
    private Integer sortOrder;

}