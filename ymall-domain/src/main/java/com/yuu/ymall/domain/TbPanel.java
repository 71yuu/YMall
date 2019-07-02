package com.yuu.ymall.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 板块实体
 */
@Data
public class TbPanel implements Serializable {
    /**
     * 板块 id
     */
    private Integer id;

    /**
     * 板块名称
     */
    private String name;

    /**
     * 板块类型 0 轮播图 1 板块种类一 2  板块种类二 3 板块种类三
     */
    private Integer type;

    /**
     * 排列序号
     */
    private Integer sortOrder;

    /**
     * 所属位置 0 首页 1 商品推荐 2 我要捐赠
     */
    private Integer position;

    /**
     * 板块限制商品数量
     */
    private Integer limitNum;

    /**
     * 板块状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

}
