package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbOrder;

import java.util.List;

public interface TbOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbOrder record);

    TbOrder selectByPrimaryKey(String id);

    List<TbOrder> selectAll();

    int updateByPrimaryKey(TbOrder record);
}