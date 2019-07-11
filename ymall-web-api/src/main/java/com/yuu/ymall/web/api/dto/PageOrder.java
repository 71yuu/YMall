package com.yuu.ymall.web.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 订单分页信息
 *
 * @author by Yuu
 * @classname PageOrder
 * @date 2019/7/8 0:32
 */
public class PageOrder implements Serializable {

    /**
     * 总条数
     */
    private int total;

    /**
     * 分页数据
     */
    private List<Order> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
