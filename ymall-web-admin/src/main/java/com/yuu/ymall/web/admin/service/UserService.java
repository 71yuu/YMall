package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.domain.TbUser;

import java.util.Set;

/**
 * @Classname UserService
 * @Date 2019/5/11 18:49
 * @Created by Yuu
 */
public interface UserService {

    /**
     * 根据用户名获取用户地所有的角色
     *
     * @param username
     * @return
     */
    Set<String> getRoles(String username);

    /**
     * 根据用户名获取用户所有的权限
     *
     * @param username
     * @return
     */
    Set<String> getPermissions(String username);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    TbUser getUserByUsername(String username);
}
