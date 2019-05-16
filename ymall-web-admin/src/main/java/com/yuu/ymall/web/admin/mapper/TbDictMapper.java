package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbDict;

import java.util.List;

public interface TbDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbDict record);

    TbDict selectByPrimaryKey(Integer id);

    List<TbDict> selectAll();

    int updateByPrimaryKey(TbDict record);
}