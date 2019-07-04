package com.yuu.ymall.web.api.dto;

import com.yuu.ymall.web.api.domain.ESItem;

import java.util.List;

/**
 * 分类商品查询结果
 *
 * @author by Yuu
 * @classname CateProductsResult
 * @date 2019/7/4 10:28
 */
public class CateProductsResult {

    /**
     * 查询总条数
     */
    private long total;

    /**
     * 商品集合
     */
    private List<ESItem> data;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<ESItem> getData() {
        return data;
    }

    public void setData(List<ESItem> data) {
        this.data = data;
    }
}
