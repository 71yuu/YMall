package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 板块内容实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbPanelContent extends BaseEntity {
    /**
     * 板块内容 id
     */
    private Integer id;

    /**
     * 所属板块 id
     */
    private Integer panelId;

    /**
     * 板块内容类型 0 关联商品 1 其他链接
     */
    private Integer type;

    /**
     * 关联商品 id
     */
    private Long productId;

    /**
     * 排列序号
     */
    private Integer sortOrder;

    /**
     * 其它链接
     */
    private String fullUrl;

    /**
     * 3d 轮播图备用1
     */
    private String picUrl;

    /**
     * 3d 轮播图备用2
     */
    private String picUrl2;

    /**
     * 3d 轮播图备用3
     */
    private String picUrl3;

    /**
     * 关联商品价格
     */
    private BigDecimal salePrice;

    /**
     * 关联商品名称
     */
    private String productName;

    /**
     * 关联商品标题
     */
    private String subTitle;
}