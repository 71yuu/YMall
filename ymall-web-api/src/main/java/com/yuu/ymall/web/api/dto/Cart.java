package com.yuu.ymall.web.api.dto;

import java.io.Serializable;

/**
 * 购物车
 *
 * @author by Yuu
 * @classname Cart
 * @date 2019/7/3 9:10
 */
public class Cart implements Serializable {

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 商品 id
     */
    private Long productId;

    /**
     * 是否选中
     */
    private String checked;

    /**
     * 商品数量
     */
    private int productNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }
}
