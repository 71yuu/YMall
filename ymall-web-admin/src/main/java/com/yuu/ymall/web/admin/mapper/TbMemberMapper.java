package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbMember;

import java.util.List;

public interface TbMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbMember record);

    TbMember selectByPrimaryKey(Long id);

    List<TbMember> selectAll();

    int updateByPrimaryKey(TbMember record);

    int getAllMemberCount();
}