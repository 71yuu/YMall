package com.yuu.ymall.web.api.web;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbMember;
import com.yuu.ymall.web.api.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 前台会员控制器
 *
 * @author by Yuu
 * @classname MemberController
 * @date 2019/6/24 8:57
 */
@RestController
@Api(description = "会员接口")
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 验证手机号是否存在
     *
     * @param phone 手机号
     * @return
     */
    @GetMapping("checkphone/{phone}")
    @ApiOperation(value = "验证手机号是存在")
    public BaseResult checkPhone(@PathVariable String phone) {
        BaseResult baseResult = memberService.checkPhone(phone);
        return baseResult;
    }

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return
     */
    @GetMapping("sendsms/{phone}")
    @ApiOperation(value = "发送短信验证码")
    public BaseResult sendSms(@PathVariable String phone) {
        BaseResult baseResult = memberService.sendSms(phone);
        return baseResult;
    }

    /**
     * 会员注册
     *
     * @param tbMember 会员
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "会员注册")
    public BaseResult register(@RequestBody TbMember tbMember) {
        BaseResult baseResult = memberService.register(tbMember);
        return baseResult;
    }
}
