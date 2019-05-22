package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbDict extends BaseEntity {
    /**
     * 字典id
     */
    private Integer id;

    /**
     * 字典
     */
    private String dict;

    /**
     * 字典类型
     */
    private Integer type;

}