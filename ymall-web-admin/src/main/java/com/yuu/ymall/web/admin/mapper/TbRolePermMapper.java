package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbRolePerm;

import java.util.List;

public interface TbRolePermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRolePerm record);

    TbRolePerm selectByPrimaryKey(Integer id);

    List<TbRolePerm> selectAll();

    int updateByPrimaryKey(TbRolePerm record);
}