package com.yuu.ymall.web.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单支付接受请求
 *
 * @author by Yuu
 * @classname OrderPay
 * @date 2019/7/6 15:55
 */
public class OrderPay implements Serializable {

    /**
     * 订单 id
     */
    private Long orderId;

    /**
     * 订单支付总金额
     */
    private BigDecimal orderTotal;

    /**
     * 订单支付状态
     */
    private String orderStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
