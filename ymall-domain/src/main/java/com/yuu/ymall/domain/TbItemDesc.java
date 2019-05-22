package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品描述实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbItemDesc extends BaseEntity {
    /**
     * 商品描述 id
     */
    private Long id;

    /**
     * 商品描述信息
     */
    private String itemDesc;
}