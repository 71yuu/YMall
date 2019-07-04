package com.yuu.ymall.web.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车商品
 *
 * @author by Yuu
 * @classname CartProduct
 * @date 2019/7/3 10:08
 */
public class CartProduct implements Serializable {

    /**
     * 商品 id
     */
    private Long productId;

    /**
     * 商品售价
     */
    private BigDecimal salePrice;

    /**
     * 商品数量
     */
    private int productNum;

    /**
     * 商品限制购买数量
     */
    private int limitNum;

    /**
     * 商品是否被选中
     */
    private String checked;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片地址
     */
    private String productImg;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
