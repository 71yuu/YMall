package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbMember;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname MemberController
 * @Date 2019/5/15 22:08
 * @Created by Yuu
 */
@RestController
@Api(description = "会员管理")
@RequestMapping("member")
public class MemberController {

    private final static Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    /**
     * 获取总会员数目
     *
     * @return
     */
    @GetMapping("count")
    @ApiOperation(value = "获取总会员数目")
    public BaseResult getAllMemberCount() {
        int memberCount = memberService.getAllMemberCount();
        return BaseResult.success(memberCount);
    }

    /**
     * 获取会员列表
     *
     * @param request 请求
     * @param search 搜索条件
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "获取会员列表")
    public DataTablesResult<TbMember> getMemberList(HttpServletRequest request, @RequestParam("search[value]") String search) {
        DataTablesResult<TbMember> dataTablesResult = memberService.getMemberList(request, search);
        return dataTablesResult;
    }

    /**
     * 封禁会员
     *
     * @param id 会员 id
     * @return
     */
    @DeleteMapping("ban/{id}")
    @ApiOperation(value = "封禁会员")
    public BaseResult banMember(@PathVariable Long id) {
        BaseResult baseResult = memberService.banMember(id);
        return baseResult;
    }

    /**
     * 解封会员
     *
     * @param id 会员 id
     * @return
     */
    @DeleteMapping("start/{ids}")
    @ApiOperation(value = "解封会员")
    public BaseResult startMember(@PathVariable Long[] ids) {
        BaseResult baseResult = memberService.startMember(ids);
        return baseResult;
    }

    /**
     * 验证注册会员名是否存在
     *
     * @param username 会员名
     * @return
     */
    @GetMapping("username")
    @ApiOperation(value = "验证注册会员名是否存在")
    public Boolean validateUsername(String username, Long id) {
        Boolean flag = memberService.getMemberByUsername(username, id);
        return flag;
    }

    /**
     * 验证注册手机号是否存在
     *
     * @param phone 手机号
     * @return
     */
    @GetMapping("phone")
    @ApiOperation(value = "验证注册手机号是否存在")
    public Boolean validatePhone(String phone, Long id) {
        Boolean flag = memberService.getMemberByPhone(phone, id);
        return flag;
    }

    /**
     * 验证注册邮箱是否存在
     *
     * @param email 邮箱
     * @return
     */
    @GetMapping("email")
    @ApiOperation(value = "验证注册邮箱是否存在")
    public Boolean validateEmail(String email, Long id) {
        Boolean flag = memberService.getMemberByEmail(email, id);
        return flag;
    }

    /**
     * 保存会员
     *
     * @param tbMember 会员
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "保存会员")
    public BaseResult saveMember(TbMember tbMember) {
        BaseResult baseResult = memberService.saveMember(tbMember);
        return baseResult;
    }

    /**
     * 修改会员密码
     *
     * @param id 会员 id
     * @param tbMember 会员
     * @return
     */
    @PostMapping("changePass/{id}")
    @ApiOperation(value = "修改会员密码")
    public BaseResult changeMemberPassword(@PathVariable Long id, String password) {
        BaseResult baseResult = memberService.changeMemberPassword(id, password);
        return baseResult;
    }

    /**
     * 删除会员
     *
     * @param ids 会员 id 集合
     * @return
     */
    @DeleteMapping("delete/{ids}")
    @ApiOperation(value = "删除会员")
    public BaseResult deleteMember(@PathVariable Long[] ids) {
        BaseResult baseResult = memberService.deleteMember(ids);
        return baseResult;
    }

    /**
     * 封禁的会员列表
     *
     * @param request 请求
     * @param search 搜索条件
     * @return
     */
    @GetMapping("ban/list")
    @ApiOperation(value = "封禁的会员列表")
    public DataTablesResult<TbMember> getBanMemberList(HttpServletRequest request, @RequestParam("search[value]") String search) {
        DataTablesResult<TbMember> dataTablesResult = memberService.getMemberBanList(request, search);
        return dataTablesResult;
    }
}
