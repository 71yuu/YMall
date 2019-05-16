package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbShiroFilter;

import java.util.List;

public interface TbShiroFilterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbShiroFilter record);

    TbShiroFilter selectByPrimaryKey(Integer id);

    List<TbShiroFilter> selectAll();

    int updateByPrimaryKey(TbShiroFilter record);

    List<TbShiroFilter> getShiroFilter();
}