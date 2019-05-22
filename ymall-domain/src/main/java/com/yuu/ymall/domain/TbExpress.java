package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;

import lombok.EqualsAndHashCode;

/**
 * 快递实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbExpress extends BaseEntity {
    /**
     * 快递 id
     */
    private Integer id;

    /**
     * 快递名称
     */
    private String expressName;

    /**
     * 排列序号
     */
    private Integer sortOrder;

}