package com.yuu.ymall.web.api.web;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.geetest.GeetestLib;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.domain.TbMember;
import com.yuu.ymall.web.api.common.dto.MemberLogin;
import com.yuu.ymall.web.api.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

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

    @Autowired
    private RedisCacheManager redisCacheManager;

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

    /**
     * 登录极验初始化
     *
     * @param request
     * @return
     */
    @GetMapping("geetestInit")
    @ApiOperation(value = "极验初始化")
    public String geetestInit(HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);
        String resStr = "{}";
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        // 进行验证预处理
        int getServerStatus = gtSdk.preProcess(param);
        resStr = gtSdk.getResponseStr();
        return resStr;
    }

    /**
     * 会员登录
     *
     * @param memberLogin 会员登录信息
     * @return
     */
    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public BaseResult login(@RequestBody MemberLogin memberLogin, HttpSession session) {
        BaseResult baseResult = memberService.login(memberLogin, session);
        return baseResult;
    }

    /**
     * 验证会员是否登录
     *
     * @param token 会员本地 token
     * @return
     */
    @GetMapping("checkLogin")
    @ApiOperation(value = "验证用户是否登录")
    public BaseResult checkLogin(@RequestParam(defaultValue = "") String token, HttpSession session) {
        // 会员信息存在 session 中
        if ("".equals(token)) {
            // 判判断 HttpSession 中是否有登录信息
            TbMember tbMember = (TbMember) session.getAttribute("memberLogin");
            if (tbMember != null) {
                // 用户未选择自动登录，session 中有登录信息
                return BaseResult.success(tbMember);
            } else {
                return BaseResult.fail("用户未登录！");
            }
        }
        // 会员信息存在 Redis 中
        BaseResult baseResult = memberService.getMemberByToken(token);
        return baseResult;
    }

    /**
     * 退出登录
     *
     * @param token 会员本地的 token
     * @return
     */
    @GetMapping("logout")
    @ApiOperation(value = "退出登录")
    public BaseResult logout(@RequestParam(defaultValue = "") String token, HttpSession session) {
        // 从 session 中退出登录
        if ("".equals(token)) {
            session.removeAttribute("memberLogin");
            return BaseResult.success("退出登录成功!");
        }
        // 从 Redis 中退出登录
        BaseResult baseResult = memberService.logout(token);
        return baseResult;
    }

    /**
     * 验证手机号或邮箱是否存在
     *
     * @param account 手机号或邮箱
     * @return
     */
    @GetMapping("checkAccount")
    @ApiOperation(value = "验证手机号或邮箱是否存在")
    public BaseResult checkAccount(@RequestParam(defaultValue = "") String account) {
        BaseResult baseResult = memberService.checkAccount(account);
        return baseResult;
    }

    /**
     * 会员修改密码
     *
     * @param account 手机号或邮箱
     * @param password 密码
     * @return
     */
    @PostMapping("updatePassword")
    @ApiOperation(value = "会员修改密码")
    public BaseResult updatePassword(@RequestBody MemberLogin memberLogin) {
        BaseResult baseResult = memberService.updatePassword(memberLogin);
        return baseResult;
    }

    /**
     * 忘记密码发送验证码
     *
     * @param account 手机号或邮箱
     * @return
     */
    @GetMapping("forgetVerCode")
    @ApiOperation(value = "")
    public BaseResult forgetVerCode(@RequestParam(defaultValue = "") String account) {
        BaseResult baseResult = memberService.sendVerCode(account);
        return baseResult;
    }
}
