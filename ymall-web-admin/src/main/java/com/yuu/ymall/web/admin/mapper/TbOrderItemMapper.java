package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbOrderItem;

import java.util.List;

public interface TbOrderItemMapper extends BaseMapper<TbOrderItem> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 获取本周热门商品
     *
     * @return
     */
    List<TbOrderItem> getWeekHot();

    /**
     * 查看商品是否有订单项
     *
     * @param id 商品 id
     * @return
     */
    int selectByItemId(Long id);

    /**
     * 根据订单 id，查询所有订单项
     *
     * @param id 订单 id
     * @return
     */
    List<TbOrderItem> selectOrderItemByOrderId(String orderId);

    /**
     * 查询该商品订单数量
     *
     * @param itemId 商品 id
     * @return
     */
    int selectOrderNumByItemId(Long itemId);
}
