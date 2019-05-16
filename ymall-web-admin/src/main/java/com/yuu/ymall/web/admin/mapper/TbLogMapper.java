package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbLog;

import java.util.List;

public interface TbLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbLog record);

    TbLog selectByPrimaryKey(Integer id);

    List<TbLog> selectAll();

    int updateByPrimaryKey(TbLog record);
}