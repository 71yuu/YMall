package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbOrder;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname OrderService
 * @Date 2019/5/15 22:35
 * @Created by Yuu
 */
public interface OrderService {

    /**
     * 获取订单总数
     *
     * @return
     */
    int getAllOrderCount();

    /**
     * 获取订单列表
     *
     * @param request 请求
     * @param search 查询条件
     * @param status 订单状态
     * @return
     */
    DataTablesResult<TbOrder> getOrderList(HttpServletRequest request, String search, int status);

    /**
     * 获取订单详情
     *
     * @param id 订单 id
     * @return
     */
    BaseResult getOrderDetail(String id);

    /**
     * 订单发货
     *
     * @param orderId 订单 id
     * @param shippingName 快递名称
     * @param shippingCode 快递单号
     * @return
     */
    BaseResult deliver(String orderId, String shippingName, String shippingCode);

    /**
     * 管理员取消订单
     *
     * @param orderId 订单id
     * @return
     */
    BaseResult cancelOrderByAdmin(String orderId);

    /**
     * 根据订单 id 集合删除订单
     *
     * @param ids 订单 id 集合
     * @return
     */
    BaseResult deleteOrderByIds(String[] ids);
}
