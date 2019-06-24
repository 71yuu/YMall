package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbMember;

import java.util.List;

public interface TbMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbMember record);

    TbMember selectByPrimaryKey(Long id);

    List<TbMember> selectAll();

    int updateByPrimaryKey(TbMember record);

    /**
     * 根据手机号查询会员
     *
     * @param phone 手机号
     * @return
     */
    TbMember selectByPhone(String phone);

    /**
     * 会员注册
     *
     * @param tbMember 会员
     */
    void register(TbMember tbMember);
}