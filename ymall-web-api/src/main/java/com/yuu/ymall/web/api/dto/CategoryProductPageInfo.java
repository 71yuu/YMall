package com.yuu.ymall.web.api.dto;

import java.io.Serializable;

/**
 * 分类商品查询分页信息
 *
 * @author by Yuu
 * @classname ProductPageInfo
 * @date 2019/7/4 10:16
 */
public class CategoryProductPageInfo implements Serializable {

    /**
     * 当前第几页
     */
    private Integer page;

    /**
     * 每页显示的个数
     */
    private Integer size;

    /**
     * 排序条件
     */
    private String sort;

    /**
     * 查询条件
     */
    private String key;

    /**
     * 分类id
     */
    private Long cid;

    /**
     * 最高价格
     */
    private Integer priceGt;

    /**
     * 最低价格
     */
    private Integer priceLte;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getPriceGt() {
        return priceGt;
    }

    public void setPriceGt(Integer priceGt) {
        this.priceGt = priceGt;
    }

    public Integer getPriceLte() {
        return priceLte;
    }

    public void setPriceLte(Integer priceLte) {
        this.priceLte = priceLte;
    }
}
