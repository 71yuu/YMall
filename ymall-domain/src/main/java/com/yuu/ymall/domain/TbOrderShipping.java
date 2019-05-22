package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单物流信息实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbOrderShipping extends BaseEntity {
    /**
     * 订单物流 id
     */
    private String id;

    /**
     * 收货人全名
     */
    private String receiverName;

    /**
     * 固定电话
     */
    private String receiverPhone;

    /**
     * 移动电话
     */
    private String receiverMobile;

    /**
     * 省份
     */
    private String receiverState;

    /**
     * 城市
     */
    private String receiverCity;

    /**
     * 区/县
     */
    private String receiverDistrict;

    /**
     * 收获地址
     */
    private String receiverAddress;

    /**
     * 邮政编码
     */
    private String receiverZip;
}