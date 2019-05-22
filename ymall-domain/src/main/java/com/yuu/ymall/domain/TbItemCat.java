package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品类目实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbItemCat extends BaseEntity {
    /**
     * 类目 id
     */
    private Long id;

    /**
     * 父分类 ID = 0 代表一级根分类
      */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类状态
     */
    private Integer status;

    /**
     * 排列序号
     */
    private Integer sortOrder;

    /**
     * 是否为父分类 1 为 true 0 为 false
     */
    private Boolean isParent;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 分类备注
     */
    private String remark;

}