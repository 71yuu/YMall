package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.admin.commons.utils.IPInfoUtil;
import com.yuu.ymall.web.admin.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统Controller
 *
 * @Classname SystemController
 * @Date 2019/5/12 21:54
 * @Created by Yuu
 */
@RestController
@Api(description = "系统配置管理")
@RequestMapping("sys")
public class SystemController {

    @Autowired
    private SystemService systemService;

    /**
     * 获取天气信息
     *
     * @param request
     * @return
     */
    @GetMapping("weather")
    @ApiOperation(value = "获取天气信息")
    public BaseResult getWeather(HttpServletRequest request) {
        // todo 修改 ip
         String result = IPInfoUtil.getIpInfo(IPInfoUtil.getIpAddr(request));
        return BaseResult.success((Object)result);
    }

    /**
     * 获取本周热销商品数据
     *
     * @return
     */
    @GetMapping("weekHot")
    @ApiOperation(value = "获取本周热销商品数据")
    public BaseResult getWeekHot() {
        BaseResult baseResult = systemService.getWeekHot();
        return baseResult;
    }
}
