package com.yuu.ymall.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 快递实体
 */
@Data
public class TbExpress implements Serializable {
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

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

}