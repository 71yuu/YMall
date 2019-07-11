package com.yuu.ymall.web.api.dto;

import com.yuu.ymall.domain.TbAddress;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详情
 *
 * @author by Yuu
 * @classname Order
 * @date 2019/7/6 12:10
 */
public class Order implements Serializable {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单所属会员 id
     */
    private String userId;

    /**
     * 订单总计
     */
    private BigDecimal orderTotal;

    /**
     * 订单地址信息
     */
    private TbAddress tbAddress;

    /**
     * 订单商品集合
     */
    private List<CartProduct> goodsList;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单创建时间
     */
    private String createDate;

    /**
     * 订单发货时间
     */
    private String consignDate;

    /**
     * 订单关闭时间
     */
    private String closeDate;

    /**
     * 订单完成时间
     */
    private String finishDate;

    /**
     * 订单支付时间
     */
    private String payDate;

    /**
     * 最晚支付时间
     */
    private String countTime;

    /**
     * 订单物流名称
     */
    private String shippingName;

    /**
     * 订单物流单号
     */
    private String shippingCode;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public TbAddress getTbAddress() {
        return tbAddress;
    }

    public void setTbAddress(TbAddress tbAddress) {
        this.tbAddress = tbAddress;
    }

    public List<CartProduct> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CartProduct> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getConsignDate() {
        return consignDate;
    }

    public void setConsignDate(String consignDate) {
        this.consignDate = consignDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }
}


