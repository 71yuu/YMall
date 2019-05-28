package com.yuu.ymall.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 页面跳转
 *
 * @Classname PageController
 * @Date 2019/5/11 22:22
 * @Created by Yuu
 */
@Controller
public class PageController {

    /**
     * 跳转到首页
     *
     * @return
     */
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * 通用的跳转方法
     *
     * @param page
     * @return
     */
    @GetMapping("{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * 通用的板块内容管理页面跳转
     *
     * @param type 类型 -1 板块内容管理 0 轮播图管理
     * @return
     */
    @GetMapping("content-common-list")
    public String contentCommonListPage(@ModelAttribute("type") Integer type) {
        return "content-common-list";
    }
}

