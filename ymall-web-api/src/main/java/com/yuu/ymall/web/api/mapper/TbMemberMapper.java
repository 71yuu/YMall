package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbMember;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据邮箱查询会员
     *
     * @param email 邮箱
     * @return
     */
    TbMember selectByEmail(String email);

    /**
     * 根据手机号或者邮箱查询会员
     *
     * @param account 手机号或邮箱
     * @return
     */
    TbMember selectByPhoneOrEmail(String account);

    /**
     * 会员修改密码
     *
     * @param account 手机号或邮箱
     * @param password 密码
     * @return
     */
    Integer updatePassword(@Param("account") String account, @Param("password") String password);
}
