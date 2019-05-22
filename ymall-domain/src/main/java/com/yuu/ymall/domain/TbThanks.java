package com.yuu.ymall.domain;

import com.yuu.ymall.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 捐助感谢名单
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbThanks extends BaseEntity {
    /**
     * 捐赠感谢 id
     */
    private Integer id;

    /**
     * 捐赠人昵称
     */
    private String nickName;

    /**
     * 捐赠人用户名
     */
    private String username;

    /**
     * 捐赠金额
     */
    private BigDecimal money;

    /**
     * 备注信息
     */
    private String info;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 支付状态
     */
    private Integer state;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 订单 id
     */
    private String orderId;

}