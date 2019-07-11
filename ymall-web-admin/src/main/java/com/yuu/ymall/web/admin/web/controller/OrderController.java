package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbOrder;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取订单列表
     *
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "获取订单列表")
    public DataTablesResult<TbOrder> getOrderList(HttpServletRequest request, @RequestParam("search[value]") String search,@RequestParam(defaultValue = "-1") int status) {
        DataTablesResult<TbOrder> result = orderService.getOrderList(request, search, status);
        return result;
    }

    /**
     * 获取订单详情
     *
     * @param id 订单 id
     * @return
     */
    @GetMapping("detail/{id}")
    @ApiOperation(value = "获取订单详情")
    public BaseResult getOrderDetail(@PathVariable String id) {
        BaseResult baseResult = orderService.getOrderDetail(id);
        return baseResult;
    }

    /**
     * 订单发货
     *
     * @param orderId 订单 id
     * @param shippingName 快递名称
     * @param shippingCode 快递单号
     * @param postFee 运费
     * @return
     */
    @PostMapping("deliver")
    @ApiOperation(value = "订单发货")
    public BaseResult deliver(@RequestParam String orderId,
                              @RequestParam String shippingName,
                              @RequestParam String shippingCode) {
        BaseResult baseResult = orderService.deliver(orderId, shippingName, shippingCode);
        return baseResult;
    }

    /**
     * 管理员取消订单
     *
     * @param orderId 订单 id
     * @return
     */
    @DeleteMapping("cancel/{orderId}")
    @ApiOperation(value = "订单取消")
    public BaseResult cancelOrderByAdmin(@PathVariable String orderId) {
        BaseResult baseResult = orderService.cancelOrderByAdmin(orderId);
        return baseResult;
    }

    /**
     * 删除订单
     *
     * @param ids 订单集合
     * @return
     */
    @DeleteMapping("delete/{ids}")
    @ApiOperation(value = "删除订单")
    public BaseResult deleteOrderByIds(@PathVariable String[] ids) {
        BaseResult baseResult = orderService.deleteOrderByIds(ids);
        return baseResult;
    }
}
