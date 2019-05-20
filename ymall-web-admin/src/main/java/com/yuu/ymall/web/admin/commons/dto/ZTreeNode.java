package com.yuu.ymall.web.admin.commons.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Classname ZTreeNode
 * @Date 2019/5/20 8:48
 * @Created by Yuu
 */
@Getter
@Setter
public class ZTreeNode implements Serializable {

    private int id;

    private int pId;

    private String name;

    private Boolean isParent;

    private Boolean open;

    private String icon;

    private int status;

    private int sortOrder;

    private String remark;

    /**
     * 板块限制商品数量
     */
    private int limitNum;

    /**
     * 板块类型
     */
    private int type;

}
