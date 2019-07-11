package com.yuu.ymall.web.api.service;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.model.TradeStatus;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.api.dto.Order;
import com.yuu.ymall.web.api.dto.OrderInfo;
import com.yuu.ymall.web.api.dto.OrderPay;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by Yuu
 * @classname OrderService
 * @date 2019/7/6 2:14
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderInfo 订单信息
     * @param request 请求 ip 验证
     * @return
     */
    BaseResult addOrder(OrderInfo orderInfo, HttpServletRequest request);

    /**
     * 根据订单 id 获取订单详情
     *
     * @param orderId 订单 id
     * @return
     */
    BaseResult getOrderDet(String orderId);

    /**
     * 订单支付
     *
     * @param orderPay 订单支付
     * @return
     */
    AlipayTradePrecreateResponse payment(OrderPay orderPay);

    /**
     * 修改订单状态
     *
     * @param orderId 订单 id
     * @param orderStatus 订单状态
     */
    int updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 获取订单支付状态
     *
     * @param orderId 订单 id
     * @return
     */
    TradeStatus getOrderStatus(String orderId);

    /**
     * 获取订单列表
     *
     * @param userId 会员 id
     * @param page 第几页
     * @param size 每页大小
     * @return
     */
    BaseResult getOrderList(String userId, int page, int size);

    /**
     * 确认收货
     *
     * @param order 订单
     * @return
     */
    BaseResult confirmReceipt(Order order);

    /**
     * 删除订单
     *
     * @param order 订单
     * @return
     */
    BaseResult deleteService(Order order);

    /**
     * 支付成功
     *
     * @param orderId 订单 id
     * @return
     */
    int paySuccess(String orderId);

    /**
     * 取消订单
     *
     * @param order 订单
     * @return
     */
    BaseResult cancelService(Order order);
}
