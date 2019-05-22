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
}