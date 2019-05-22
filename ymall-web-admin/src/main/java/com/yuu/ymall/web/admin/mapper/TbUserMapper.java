package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbUser;

import java.util.Set;

public interface TbUserMapper extends BaseMapper<TbUser> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 获取用户的所有角色
     *
     * @param username 用户名
     * @return
     */
    Set<String> getRoles(String username);

    /**
     * 获取用户的所有权限
     *
     * @param username 用户名
     * @return
     */
    Set<String> getPermissions(String username);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return
     */
    TbUser getUserByUsername(String username);
}