package com.yuu.ymall.web.api.service.impl;

import com.yuu.ymall.commons.consts.Consts;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbMember;
import com.yuu.ymall.web.api.mapper.TbMemberMapper;
import com.yuu.ymall.web.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author by Yuu
 * @classname MemberServieImpl
 * @date 2019/6/24 9:05
 */
@Service
@Transactional(readOnly = true)
public class MemberServieImpl implements MemberService {

    @Autowired
    private TbMemberMapper tbMemberMapper;

    @Override
    public BaseResult checkPhone(String phone) {
        TbMember tbMember = tbMemberMapper.selectByPhone(phone);

        // 手机号已存在
        if (tbMember != null) {
            return BaseResult.success(true);
        }

        return BaseResult.success(false);
    }

    @Override
    public BaseResult sendSms(String phone) {
        // 调用短信发送工具发送短信,返回验证码
      /*  String vercode = SendSmsUtil.sendSms(phone);
        return BaseResult.success((Object)vercode);*/
      return BaseResult.success((Object)"123456");
    }

    @Transactional
    @Override
    public BaseResult register(TbMember tbMember) {
        tbMember.setPassword(DigestUtils.md5DigestAsHex(tbMember.getPassword().getBytes()));
        tbMember.setCreated(new Date());
        tbMember.setUpdated(new Date());
        tbMember.setState(Consts.MEMBER_STATUS_NORMAL);
        tbMemberMapper.register(tbMember);
        return BaseResult.success();
    }
}
