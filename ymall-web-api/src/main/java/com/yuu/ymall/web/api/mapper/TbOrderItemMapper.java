package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbOrderItem;

import java.util.List;

public interface TbOrderItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbOrderItem record);

    TbOrderItem selectByPrimaryKey(String id);

    List<TbOrderItem> selectAll();

    int updateByPrimaryKey(TbOrderItem record);

    /**
     * 根据订单id，查询订单商品
     *
     * @param id 订单 id
     * @return
     */
    List<TbOrderItem> selectByOrderId(String orderId);

    /**
     * 根据订单删除订单商品
     *
     * @param orderId 订单 id
     * @return
     */
    int deleteByOrderId(String orderId);
}
