package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbUser;

import java.util.List;
import java.util.Set;

public interface TbUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    List<TbUser> selectAll();

    int updateByPrimaryKey(TbUser record);

    Set<String> getRoles(String username);

    Set<String> getPermissions(String username);

    TbUser getUserByUsername(String username);
}