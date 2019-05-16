package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbOrderShipping;

import java.util.List;

public interface TbOrderShippingMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(TbOrderShipping record);

    TbOrderShipping selectByPrimaryKey(String orderId);

    List<TbOrderShipping> selectAll();

    int updateByPrimaryKey(TbOrderShipping record);
}