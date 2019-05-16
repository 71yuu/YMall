package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbAddress;

import java.util.List;

public interface TbAddressMapper {
    int deleteByPrimaryKey(Long addressId);

    int insert(TbAddress record);

    TbAddress selectByPrimaryKey(Long addressId);

    List<TbAddress> selectAll();

    int updateByPrimaryKey(TbAddress record);
}