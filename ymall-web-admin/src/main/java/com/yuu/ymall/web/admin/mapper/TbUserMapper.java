package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbUserMapper extends BaseMapper<TbUser> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return
     */
    TbUser getUserByUsername(String username);

    /**
     * 获取用户列表
     *
     * @param search 搜索条件
     * @return
     */
    List<TbUser> getUserList(@Param("search") String search);

    /**
     * 获取用户总数
     *
     * @param search 搜索条件
     * @return
     */
    int getUserListCount(@Param("search") String search);

    /**
     * 根据手机号获取用户
     *
     * @param phone 手机号
     * @return
     */
    TbUser getUserByPhone(String phone);

    /**
     * 根据邮箱获取用户
     *
     * @param email 邮箱
     * @return
     */
    TbUser getUserByEmail(String email);
}