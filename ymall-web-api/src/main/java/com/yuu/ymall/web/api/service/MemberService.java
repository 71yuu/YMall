package com.yuu.ymall.web.api.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbMember;

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

}
