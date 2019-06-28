package com.yuu.ymall.web.api.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbMember;
import com.yuu.ymall.web.api.common.dto.MemberLogin;

import javax.servlet.http.HttpSession;

/**
 * 会员服务提供者
 *
 * @author by Yuu
 * @classname MemberService
 * @date 2019/6/24 9:04
 */
public interface MemberService {

    /**
     * 验证手机号是否存在
     *
     * @param phone
     * @return
     */
    BaseResult checkPhone(String phone);

    /**
     * 发送短信
     *
     * @param phone 手机号
     * @return
     */
    BaseResult sendSms(String phone);

    /**
     * 会员注册
     *
     * @param tbMember 会员
     * @return
     */
    BaseResult register(TbMember tbMember);

    /**
     * 会员登录
     *
     * @param memberLogin 会员登录信息
     * @param session 会话信息
     * @return
     */
    BaseResult login(MemberLogin memberLogin, HttpSession session);

    /**
     * 判断缓存中是否有登录信息
     *
     * @param token 令牌
     * @return
     */
    BaseResult getMemberByToken(String token);

    /**
     * 从 Redis 中退出登录
     *
     * @param token 会员本地保存的 token
     * @return
     */
    BaseResult logout(String token);

    /**
     * 验证手机号或邮箱是否存在
     *
     * @param account 手机号或邮箱
     * @return
     */
    BaseResult checkAccount(String account);

    /**
     * 会员修改密码
     *
     * @param memberLogin 会员信息
     * @return
     */
    BaseResult updatePassword(MemberLogin memberLogin);

    /**
     * 发送验证码
     *
     * @param account 手机号或邮箱
     * @return
     */
    BaseResult sendVerCode(String account);
}
