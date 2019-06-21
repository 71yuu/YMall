package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbMember;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname MemberService
 * @Date 2019/5/15 22:12
 * @Created by Yuu
 */
public interface MemberService {
    /**
     * 获取会员总数
     *
     * @return
     */
    int getAllMemberCount();

    /**
     * 获取会员列表
     *
     * @param request 请求
     * @param search 搜索条件
     * @return
     */
    DataTablesResult<TbMember> getMemberList(HttpServletRequest request, String search);

    /**
     * 封禁会员
     *
     * @param id 会员 id
     * @return
     */
    BaseResult banMember(Long id);

    /**
     * 解封会员
     *
     * @param id 会员 id 集合
     * @return
     */
    BaseResult startMember(Long[] ids);

    /**
     * 保存会员
     *
     * @param tbMember 会员
     * @return
     */
    BaseResult saveMember(TbMember tbMember);

    /**
     * 根据会员名获取会员
     *
     * @param username 会员名
     * @param  id 会员 id
     * @return
     */
    Boolean getMemberByUsername(String username, Long id);

    /**
     * 根据手机号获取会员
     *
     * @param phone 手机号
     * @param id 会员 id
     * @return
     */
    Boolean getMemberByPhone(String phone, Long id);

    /**
     * 根据邮箱获取会员
     *
     * @param email 邮箱
     * @param id 会员 id
     * @return
     */
    Boolean getMemberByEmail(String email, Long id);

    /**
     * 修改会员密码
     *
     * @param id 会员 id
     * @param password 会员 密码
     * @return
     */
    BaseResult changeMemberPassword(Long id, String password);

    /**
     * 删除会员
     * @param ids
     * @return
     */
    BaseResult deleteMember(Long[] ids);

    /**
     * 获取被封禁的会员列表
     *
     * @param request 请求
     * @param search 搜索条件
     * @return
     */
    DataTablesResult<TbMember> getMemberBanList(HttpServletRequest request, String search);
}
