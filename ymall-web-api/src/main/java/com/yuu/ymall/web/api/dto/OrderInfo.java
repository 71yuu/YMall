package com.yuu.ymall.web.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 接受订单信息
 *
 * @author by Yuu
 * @classname OrderInfo
 * @date 2019/7/6 2:05
 */
public class OrderInfo implements Serializable {

    /**
     * 会员 id
     */
    private Long userId;

    /**
     * 地址 id
     */
    private Long addressId;

    /**
     * 电话
     */
    private String tel;

    /**
     * 收货人
     */
    private String userName;

    /**
     * 详细地址
     */
    private String streetName;

    /**
     * 订单总计
     */
    private BigDecimal orderTotal;

    /**
     * 订单商品
     */
    private List<CartProduct> goodsList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<CartProduct> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CartProduct> goodsList) {
        this.goodsList = goodsList;
    }
}
