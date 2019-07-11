package com.yuu.ymall.web.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbOrder;
import com.yuu.ymall.domain.TbOrderItem;
import com.yuu.ymall.domain.TbOrderShipping;
import com.yuu.ymall.web.admin.commons.consts.Consts;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.commons.dto.OrderDetail;
import com.yuu.ymall.web.admin.mapper.TbOrderItemMapper;
import com.yuu.ymall.web.admin.mapper.TbOrderMapper;
import com.yuu.ymall.web.admin.mapper.TbOrderShippingMapper;
import com.yuu.ymall.web.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname OrderServiceImpl
 * @Date 2019/5/15 22:35
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Override
    public int getAllOrderCount() {
        int orderCount = tbOrderMapper.getAllOrderCount();
        return orderCount;
    }

    @Override
    public DataTablesResult<TbOrder> getOrderList(HttpServletRequest request, String search, int status) {
        DataTablesResult<TbOrder> result = new DataTablesResult<>(request);

        Map<String, Object> params = new HashMap<>();
        params.put("search", search);
        params.put("status", status);

        // 分页查询
        PageHelper.startPage(result.getPageNum(), result.getLength());
        List<TbOrder> tbOrderList = tbOrderMapper.getOrderList(params);

        // 封装 PageInfo
        PageInfo<TbOrder> tbOrderPageInfo = new PageInfo<>(tbOrderList);
        result.setRecordsFiltered((int) tbOrderPageInfo.getTotal());
        result.setRecordsTotal(tbOrderMapper.getTbOrderCount(params));
        result.setDraw(result.getDraw());
        result.setData(tbOrderList);

        return result;
    }

    @Override
    public BaseResult getOrderDetail(String id) {

        // 根据 id 查询订单信息
        OrderDetail orderDetail = new OrderDetail();
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(id);

        // 查询所有订单项
        List<TbOrderItem> tbOrderItemList = tbOrderItemMapper.selectOrderItemByOrderId(id);

        // 查询订单收货信息
        TbOrderShipping tbOrderShipping = tbOrderShippingMapper.selectByPrimaryKey(id);

        // 封装 OrderDetail
        orderDetail.setTbOrder(tbOrder);
        orderDetail.setTbOrderItemList(tbOrderItemList);
        orderDetail.setTbOrderShipping(tbOrderShipping);

        return BaseResult.success(orderDetail);
    }

    @Transactional
    @Override
    public BaseResult deliver(String orderId, String shippingName, String shippingCode) {
        // 查询出订单
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);

        // 设置快递信息
        tbOrder.setShippingName(shippingName);
        tbOrder.setShippingCode(shippingCode);
        // 发货时间
        tbOrder.setConsignTime(new Date());
        tbOrder.setUpdated(new Date());
        // 订单状态
        tbOrder.setStatus(Consts.ORDER_STATE_SHIPPED);

        // 更新订单信息
        tbOrderMapper.updateByPrimaryKey(tbOrder);
        return BaseResult.success();
    }

    @Transactional
    @Override
    public BaseResult cancelOrderByAdmin(String orderId) {
        // 查询订单
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);

        // 设置订单状态
        tbOrder.setStatus(Consts.ORDER_STATE_CLOSE);

        // 更新订单
        tbOrderMapper.updateByPrimaryKey(tbOrder);

        return BaseResult.success("取消订单成功");
    }

    @Transactional
    @Override
    public BaseResult deleteOrderByIds(String[] ids) {
        // 遍历删除订单
        for (String id : ids) {
            tbOrderMapper.deleteByPrimaryKey(id);
        }
        return BaseResult.success("删除订单成功");
    }
}
