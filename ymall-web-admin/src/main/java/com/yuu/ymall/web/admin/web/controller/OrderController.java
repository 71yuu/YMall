package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.admin.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname OrderController
 * @Date 2019/5/15 22:31
 * @Created by Yuu
 */
@RestController
@Api(description = "订单管理")
@RequestMapping("order")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单总数目
     *
     * @return
     */
    @GetMapping("count")
    @ApiOperation(value = "获取订单总数目")
    public BaseResult getOrderCount() {
        int orderCount = orderService.getAllOrderCount();
        return BaseResult.success(orderCount);
    }
}
