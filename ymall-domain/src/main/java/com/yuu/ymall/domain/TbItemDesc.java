package com.yuu.ymall.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品描述实体
 */
@Data
public class TbItemDesc implements Serializable {
    /**
     * 商品 id
     */
    private Long itemId;

    /**
     * 商品描述信息
     */
    private String itemDesc;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;
}