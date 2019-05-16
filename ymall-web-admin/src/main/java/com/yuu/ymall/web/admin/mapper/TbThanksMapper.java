package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbThanks;

import java.util.List;

public interface TbThanksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbThanks record);

    TbThanks selectByPrimaryKey(Integer id);

    List<TbThanks> selectAll();

    int updateByPrimaryKey(TbThanks record);
}