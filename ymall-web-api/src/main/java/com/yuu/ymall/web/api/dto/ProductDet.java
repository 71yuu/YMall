package com.yuu.ymall.web.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 *
 * @author by Yuu
 * @classname ProductDet
 * @date 2019/7/3 19:03
 */
public class ProductDet implements Serializable {

    /**
     * 商品 id
     */
    private Long productId;

    /**
     * 商品售价
     */
    private BigDecimal salePrice;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品子标题
     */
    private String subTitle;

    /**
     * 商品限制数量
     */
    private Integer limitNum;

    /**
     * 商品库存数量
     */
    private Integer num;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 商品大图
     */
    private String productImageBig;

    /**
     * 商品小图集合
     */
    private String[] productImageSmall;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getProductImageBig() {
        return productImageBig;
    }

    public void setProductImageBig(String productImageBig) {
        this.productImageBig = productImageBig;
    }

    public String[] getProductImageSmall() {
        return productImageSmall;
    }

    public void setProductImageSmall(String[] productImageSmall) {
        this.productImageSmall = productImageSmall;
    }
}
