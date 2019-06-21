package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbMemberMapper extends BaseMapper<TbMember> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 获取会员总数
     *
     * @return
     */
    int getAllMemberCount();

    /**
     * 获取会员列表
     *
     * @param search 查询条件
     * @return
     */
    List<TbMember> getMemberList(@Param("search") String search);

    /**
     * 获取会员列表总数
     *
     * @param search 查询条件
     * @return
     */
    int getMemberListCount(@Param("search") String search);

    /**
     * 根据会员名查询会员
     *
     * @param username 会员名
     * @return
     */
    TbMember getMemberByUsername(String username);

    /**
     * 根据手机号查询会员
     *
     * @param phone 手机号
     * @return
     */
    TbMember getMemberByPhone(String phone);

    /**
     * 根据邮箱查询会员
     *
     * @param email 邮箱
     * @return
     */
    TbMember getMemberByEmail(String email);

    /**
     * 获取被封禁的会员列表
     *
     * @param search 搜索条件
     * @return
     */
    List<TbMember> getMemberBanList(@Param("search") String search);

    /**
     * 获取被封禁的会员总数
     *
     * @param search 搜索条件
     * @return
     */
    int getMemberBanListCount(@Param("search") String search);
}