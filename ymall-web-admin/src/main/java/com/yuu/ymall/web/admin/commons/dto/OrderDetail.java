package com.yuu.ymall.web.admin.commons.dto;

import com.yuu.ymall.domain.TbOrder;
import com.yuu.ymall.domain.TbOrderItem;
import com.yuu.ymall.domain.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * 订单详情
 *
 * @Classname OrderDetail
 * @Date 2019/6/14 22:09
 * @Created by Yuu
 */
public class OrderDetail implements Serializable {

    /**
     * 订单信息
     */
    private TbOrder tbOrder;

    /**
     * 订单项集合
     */
    private List<TbOrderItem> tbOrderItemList;

    /**
     * 订单收货信息
     */
    private TbOrderShipping tbOrderShipping;

    public TbOrder getTbOrder() {
        return tbOrder;
    }

    public void setTbOrder(TbOrder tbOrder) {
        this.tbOrder = tbOrder;
    }

    public List<TbOrderItem> getTbOrderItemList() {
        return tbOrderItemList;
    }

    public void setTbOrderItemList(List<TbOrderItem> tbOrderItemList) {
        this.tbOrderItemList = tbOrderItemList;
    }

    public TbOrderShipping getTbOrderShipping() {
        return tbOrderShipping;
    }

    public void setTbOrderShipping(TbOrderShipping tbOrderShipping) {
        this.tbOrderShipping = tbOrderShipping;
    }
}
