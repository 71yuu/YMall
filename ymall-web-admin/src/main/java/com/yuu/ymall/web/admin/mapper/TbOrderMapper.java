package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbOrder;

import java.util.List;

public interface TbOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(TbOrder record);

    TbOrder selectByPrimaryKey(String orderId);

    List<TbOrder> selectAll();

    int updateByPrimaryKey(TbOrder record);

    int getAllOrderCount();
}