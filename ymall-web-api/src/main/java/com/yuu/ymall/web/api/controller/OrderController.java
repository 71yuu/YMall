package com.yuu.ymall.web.api.controller;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.model.TradeStatus;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.api.dto.Order;
import com.yuu.ymall.web.api.dto.OrderInfo;
import com.yuu.ymall.web.api.dto.OrderPay;
import com.yuu.ymall.web.api.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by Yuu
 * @classname OrderController
 * @date 2019/7/6 2:02
 */
@RestController
@RequestMapping("order")
@Api(description = "订单服务接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param orderInfo 订单信息
     * @return
     */
    @PostMapping("addOrder")
    @ApiOperation(value = "创建订单")
    public BaseResult addOrder(@RequestBody OrderInfo orderInfo, HttpServletRequest request) {
        BaseResult baseResult = orderService.addOrder(orderInfo, request);
        return baseResult;
    }

    /**
     * 通过 id 获取订单
     *
     * @param orderId 订单 id
     * @return
     */
    @GetMapping("getOrderDet")
    @ApiOperation(value = "通过id获取订单详情")
    public BaseResult getOrderDet(@RequestParam String orderId) {
        BaseResult baseResult = orderService.getOrderDet(orderId);
        return baseResult;
    }

    /**
     * 订单支付
     *
     * @param orderPay
     * @return
     */
    @PostMapping("payment")
    @ApiOperation(value = "订单支付")
    public AlipayTradePrecreateResponse payment(@RequestBody OrderPay orderPay) {
        AlipayTradePrecreateResponse response = orderService.payment(orderPay);
        return response;
    }

    /**
     * 订单异步通知
     */
    @PostMapping("callback")
    @ApiOperation(value = "订单异步通知")
    public String orderCallback(HttpServletRequest request) {
        // 订单号
        String orderId = request.getParameter("out_trade_no");

        // 交易状态
        String tradeStatus = request.getParameter("trade_status");

        // 交易创建时间
        String gmtCreate = request.getParameter("gmt_create");

        // 交易付款时间
        String gmtPayment = request.getParameter("gmt_payment");

        // 交易结束时间
        String gmtClose = request.getParameter("gmt_close");

        // 交易支付成功
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            // 修改订单状态
            orderService.paySuccess(orderId);
        }

        return "success";
    }

    /**
     * 获取订单支付状态
     *
     * @param orderId 订单 id
     * @return
     */
    @GetMapping("getOrderStatus")
    @ApiOperation(value = "获取订单支付状态")
    public TradeStatus getOrderStatus(@RequestParam String orderId) {
        TradeStatus tradeStatus = orderService.getOrderStatus(orderId);
        return tradeStatus;
    }

    /**
     * 获取用户订单列表
     *
     * @return
     */
    @GetMapping("orderList")
    @ApiOperation(value = "获取用户所有订单")
    public BaseResult getOrderList(@RequestParam String userId,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "5") int size) {
     BaseResult baseResult = orderService.getOrderList(userId, page, size);
     return baseResult;
    }

    /**
     * 确认收货
     *
     * @param orderId 订单
     * @return
     */
    @PostMapping("confirmReceipt")
    @ApiOperation(value = "确认收货")
    public BaseResult confirmReceipt(@RequestBody Order order) {
        BaseResult baseResult = orderService.confirmReceipt(order);
        return baseResult;
    }

    /**
     * 删除订单
     *
     * @param order 订单
     * @return
     */
    @PostMapping("deleteOrder")
    @ApiOperation(value = "删除订单")
    public BaseResult deleteOrder(@RequestBody Order order) {
        BaseResult baseResult = orderService.deleteService(order);
        return baseResult;
    }

    /**
     * 取消订单
     *
     * @param order 订单
     * @return
     */
    @PostMapping("cancelOrder")
    @ApiOperation(value = "取消订单")
    public BaseResult cancelOrder(@RequestBody Order order) {
        BaseResult baseResult = orderService.cancelService(order);
        return baseResult;
    }
}
