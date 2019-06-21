package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbUser;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname UserService
 * @Date 2019/5/11 18:49
 * @author by Yuu
 */
public interface UserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    TbUser getUserByUsername(String username);

    /**
     * 获取用户列表
     * @param request 请求
     * @param search 搜索条件
     * @return
     */
    DataTablesResult<TbUser> getUserList(HttpServletRequest request, String search);

    /**
     * 验证用户名是否存在
     *
     * @param id 用户 id
     * @param username 用户名
     * @return
     */
    Boolean validateUsername(Long id, String username);

    /**
     * 验证手机号是否存在
     *
     * @param id 用户 id
     * @param phone 手机号
     * @return
     */
    Boolean validatePhone(Long id, String phone);

    /**
     * 验证邮箱是否存在
     *
     * @param id 用户 id
     * @param email 邮箱
     * @return
     */
    Boolean validateEmail(Long id, String email);

    /**
     * 保存用户
     *
     * @param tbUser 用户
     * @return
     */
    BaseResult save(TbUser tbUser);

    /**
     * 修改密码
     *
     * @param id 用户 id
     * @param password 用户密码
     * @return
     */
    BaseResult changePass(Long id, String password);

    /**
     * 删除用户
     *
     * @param ids 用户 id 的集合
     * @return
     */
    BaseResult delete(Long[] ids);
}
