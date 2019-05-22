package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbItem extends BaseEntity {
    /**
     * 商品 id
     */
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer num;

    /**
     * 一次最多购买多少件
     */
    private Integer limitNum;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品分类 id
     */
    private Long cid;

    /**
     * 商品状态
     */
    private Integer status;

}