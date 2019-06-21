package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.admin.service.CountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计 Controler
 *
 * @author by Yuu
 * @classname CountController
 * @date 2019/6/21 10:56
 */
@RestController
@Api(description = "统计")
@RequestMapping("count")
public class CountController {

    @Autowired
    private CountService countService;

    /**
     * 订单销量
     *
     * @param type 类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param year 年份
     * @return
     */
    @GetMapping("order")
    @ApiOperation(value = "订单销量统计")
    public BaseResult countOrder(@RequestParam int type,
                                 @RequestParam(required = false) String startTime,
                                 @RequestParam(required = false) String endTime,
                                 @RequestParam(required = false) int year) {
        BaseResult baseResult = countService.countOrder(type, startTime, endTime, year);
        return baseResult;
    }


}
