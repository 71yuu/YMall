package com.yuu.ymall.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目实体
 */
@Data
public class TbItemCat implements Serializable {
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
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

}