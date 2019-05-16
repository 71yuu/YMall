package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbRole;

import java.util.List;

public interface TbRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRole record);

    TbRole selectByPrimaryKey(Integer id);

    List<TbRole> selectAll();

    int updateByPrimaryKey(TbRole record);
}