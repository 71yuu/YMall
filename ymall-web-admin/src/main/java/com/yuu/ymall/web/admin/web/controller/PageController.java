package com.yuu.ymall.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * 用户通用的页面跳转方法
     *
     * @param page
     * @return
     */
    @GetMapping("user/{page}")
    public String userShowPage(@PathVariable String page) {
        return page;
    }

    /**
     * 商品通用的页面跳转方法
     *
     * @param page
     * @return
     */
    @GetMapping("product/{page}")
    public String productShowPage(@PathVariable String page) {
        return page;
    }

    /**
     * 会员通用的页面跳转方法
     *
     * @param page
     * @return
     */
    @GetMapping("member/{page}")
    public String memberShowPage(@PathVariable String page) {
        return page;
    }

    /**
     * 捐赠通用的页面跳转方法
     *
     * @param page
     * @return
     */
    @GetMapping("thanks/{page}")
    public String thanksShowPage(@PathVariable String page) {
        return page;
    }

    /**
     * 跳转到欢迎页
     *
     * @param page
     * @return
     */
    @GetMapping("welcome")
    public String welcomeShowPage() {
        return "welcome";
    }

    /**
     * 内容通用的页面跳转方法
     *
     * @param page
     * @return
     */
    @GetMapping("content/{page}")
    public String contentShowPage(@PathVariable String page) {
        return page;
    }
}
