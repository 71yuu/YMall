package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订单项实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbOrderItem extends BaseEntity {
    /**
     * 订单项 id
     */
    private String id;

    /**
     * 商品 id
     */
    private String itemId;

    /**
     * 订单 id
     */
    private String orderId;

    /**
     * 商品购买数量
     */
    private Integer num;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品总金额
     */
    private BigDecimal totalFee;

    /**
     * 商品图片地址
     */
    private String picPath;

    /**
     * 本周卖出总数
     */
    private Integer total;

}