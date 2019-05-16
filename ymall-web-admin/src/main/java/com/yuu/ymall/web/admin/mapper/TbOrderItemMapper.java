package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbOrderItem;

import java.util.List;

public interface TbOrderItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbOrderItem record);

    TbOrderItem selectByPrimaryKey(String id);

    List<TbOrderItem> selectAll();

    int updateByPrimaryKey(TbOrderItem record);

    List<TbOrderItem> getWeekHot();
}