package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbPermission;

import java.util.List;

public interface TbPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbPermission record);

    TbPermission selectByPrimaryKey(Integer id);

    List<TbPermission> selectAll();

    int updateByPrimaryKey(TbPermission record);
}