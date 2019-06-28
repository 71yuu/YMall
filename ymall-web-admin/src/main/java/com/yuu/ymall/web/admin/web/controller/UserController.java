package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.geetest.GeetestLib;
import com.yuu.ymall.domain.TbUser;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 用户 Controller
 *
 * @Classname UserController
 * @Date 2019/5/11 22:20
 * @Created by Yuu
 */
@RestController
@Api(description = "管理员管理")
@RequestMapping("user")
public class UserController {

    public static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 极验初始化
     *
     * @param session
     * @return
     */
    @GetMapping("geetestInit")
    @ApiOperation(value = "极验初始化")
    public String geetestInit(HttpSession session) {

        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key, GeetestLib.newfailback);

        String resStr = "{}";

        // 自定义参数，可选择添加
        HashMap<String, String> param = new HashMap<>();

        // 进行验证预处理
        int getServerStatus = gtSdk.preProcess(param);

        session.setAttribute(gtSdk.gtServerStatusSessionKey, getServerStatus);

        resStr = gtSdk.getResponseStr();

        return resStr;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public BaseResult login(String username, String password, String challenge, String validate, String seccode, HttpSession session) {

        // 极验验证
        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);

        // 从 session 中获取 gt-server 状态
        int gt_server_status_code = (int) session.getAttribute(gtSdk.gtServerStatusSessionKey);

        // 自定义参数，可选择添加
        HashMap<String, String> param = new HashMap<>();

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            // gt-server 正常，向 gt-server 进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
        } else {
            // gt-server 非正常情况下，进行 failback 模式验证
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }

        // 验证成功
        if (gtResult == 1) {
            Subject subject = SecurityUtils.getSubject();

            // MD5 加密
            String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
            UsernamePasswordToken token = new UsernamePasswordToken(username, md5Pass);

            // 登录
            try {
                subject.login(token);
                return BaseResult.success();
            } catch (Exception e) {
                return BaseResult.fail("用户名或密码错误");
            }
        }

        // 验证失败
        else {
            return BaseResult.fail("验证失败");
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("logout")
    @ApiOperation(value = "退出登录")
    public BaseResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseResult.success();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息")
    public BaseResult getUserInfo() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        TbUser tbUser = userService.getUserByUsername(username);
        if (tbUser == null) {
            return BaseResult.fail("获取用户信息失败！");
        }
        tbUser.setPassword(null);
        return BaseResult.success(tbUser);
    }

    /**
     * 用户解锁
     *
     * @param password 密码
     * @return
     */
    @GetMapping("unlock")
    @ApiOperation(value = "用户解锁")
    public BaseResult unlock(String password) {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        TbUser tbUser = userService.getUserByUsername(username);
        if (tbUser == null) {
            return BaseResult.fail("用户不存在！");
        }
        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!tbUser.getPassword().equals(md5Pwd)) {
            return BaseResult.fail("密码不正确!");
        }
        return BaseResult.success();
    }

    /**
     * 获取用户列表
     *
     * @param request 请求
     * @param search 搜索条件
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "获取用户列表")
    public DataTablesResult<TbUser> getUserList(HttpServletRequest request, @RequestParam("search[value]") String search) {
        DataTablesResult<TbUser> dataTablesResult = userService.getUserList(request, search);
        return dataTablesResult;
    }

    /**
     * 验证用户名是否存在
     *
     * @param id 用户 id
     * @param username 用户名
     * @return
     */
    @GetMapping("username/{id}")
    @ApiOperation(value = "验证用户名是否存在")
    public Boolean validateUsername(@PathVariable Long id, String username) {
        Boolean flag = userService.validateUsername(id, username);
        return flag;
    }

    /**
     * 验证手机号是否存在
     *
     * @param id 用户 id
     * @param phone 手机号
     * @return
     */
    @GetMapping("phone/{id}")
    @ApiOperation(value = "验证手机号是否存在")
    public Boolean validatePhone(@PathVariable Long id, String phone) {
        Boolean flag = userService.validatePhone(id, phone);
        return flag;
    }

    /**
     * 验证邮箱是否存在
     *
     * @param id 用户 id
     * @param email 邮箱
     * @return
     */
    @GetMapping("email/{id}")
    @ApiOperation(value = "验证邮箱是否存在")
    public Boolean validateEmail(@PathVariable Long id, String email) {
        Boolean flag = userService.validateEmail(id, email);
        return flag;
    }

    /**
     * 保存用户
     *
     * @param tbUser 用户
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "保存用户")
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = userService.save(tbUser);
        return baseResult;
    }

    /**
     * 修改密码
     *
     * @param id 用户 id
     * @param password 用户密码
     * @return
     */
    @PostMapping("changePass")
    @ApiOperation(value = "修改密码")
    public BaseResult changePass(Long id, String password) {
        BaseResult baseResult = userService.changePass(id, password);
        return baseResult;
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @DeleteMapping("delete/{ids}")
    @ApiOperation(value = "删除用户")
    public BaseResult delete(@PathVariable Long[] ids) {
        BaseResult baseResult = userService.delete(ids);
        return baseResult;
    }

}
