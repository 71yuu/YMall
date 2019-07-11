package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbOrder record);

    TbOrder selectByPrimaryKey(String id);

    List<TbOrder> selectAll();

    int updateByPrimaryKey(TbOrder record);

    /**
     * 修改订单状态
     *
     * @param orderId 订单 id
     * @param orderStatus 订单 状态
     * @return
     */
    int updateOrderStatus(@Param("orderId") String orderId, @Param("orderStatus") Integer orderStatus);

    /**
     * 查询订单超时的订单
     *
     * @return
     */
    List<String> selectOrderOutTime();

    /**
     * 获取会员订单列表
     *
     * @param userId 会员 id
     * @return
     */
    List<TbOrder> selectByUserId(String userId);

    /**
     * 获取会员订单总数
     *
     * @param userId 会员 id
     * @return
     */
    int getMemberOrderCount(String userId);

    /**
     * 获取会员的订单信息
     *
     * @param userId 会员 id
     * @param orderId 订单 id
     * @return
     */
    TbOrder selectByUserIdAndOrderId(@Param("userId") String userId, @Param("orderId") String orderId);

    /**
     * 支付成功，修改支付时间和状态
     *
     * @param orderId 订单 id
     * @return
     */
    int updatePayTimeAndStatus(String orderId);

    /**
     * 支付失败，修改关闭时间和状态
     *
     * @param tbOrderId 订单 id
     * @return
     */
    int updateCloseTimeAndStatus(String tbOrderId);

    /**
     * 取消订单
     *
     * @param orderId 订单 id
     * @return
     */
    int cancelOrder(String orderId);

    /**
     * 确认收货
     *
     * @param orderId 订单 id
     * @return
     */
    int confirmReceipt(String orderId);
}
