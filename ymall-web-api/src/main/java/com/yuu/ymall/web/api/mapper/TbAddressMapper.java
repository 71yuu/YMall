package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbAddress;

import java.util.List;

public interface TbAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbAddress record);

    TbAddress selectByPrimaryKey(Long id);

    List<TbAddress> selectAll();

    int updateByPrimaryKey(TbAddress record);
}