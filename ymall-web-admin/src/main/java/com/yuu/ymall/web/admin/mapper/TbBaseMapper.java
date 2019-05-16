package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbBase;

import java.util.List;

public interface TbBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbBase record);

    TbBase selectByPrimaryKey(Integer id);

    List<TbBase> selectAll();

    int updateByPrimaryKey(TbBase record);

    TbBase getBase();
}