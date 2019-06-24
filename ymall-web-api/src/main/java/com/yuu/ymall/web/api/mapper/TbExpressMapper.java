package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbExpress;

import java.util.List;

public interface TbExpressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbExpress record);

    TbExpress selectByPrimaryKey(Integer id);

    List<TbExpress> selectAll();

    int updateByPrimaryKey(TbExpress record);
}