package com.yuu.ymall.web.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 板块内容数据传输对象
 *
 * @author by Yuu
 * @classname TbPanelContentDto
 * @date 2019/7/2 14:56
 */
public class TbPanelContentDto implements Serializable {

    /**
     * 板块内容 id
     */
    private Long id;

    /**
     * 板块内容类型，0 关联商品 1 其他链接
     */
    private Integer type;

    /**
     * 类型为其他链接的跳转地址
     */
    private String fullUrl;

    /**
     * 板块内容图片
     */
    private String picUrl;

    /**
     * 轮播图备用图片1
     */
    private String picUrl2;

    /**
     * 轮播图备用图片2
     */
    private String picUrl3;


    /**
     * 关联商品 id
     */
    private Long productId;

    /**
     * 关联商品售价
     */
    private BigDecimal salePrice;

    /**
     * 关联商品商品名称
     */
    private String productName;

    /**
     * 关联商品副标题
     */
    private String subTitle;

    /**
     * 商品限制购买数量
     */
    private int limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl2() {
        return picUrl2;
    }

    public void setPicUrl2(String picUrl2) {
        this.picUrl2 = picUrl2;
    }

    public String getPicUrl3() {
        return picUrl3;
    }

    public void setPicUrl3(String picUrl3) {
        this.picUrl3 = picUrl3;
    }

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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
