package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.admin.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
