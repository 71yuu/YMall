package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbOrder;
import com.yuu.ymall.web.admin.commons.dto.OrderChartData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TbOrderMapper extends BaseMapper<TbOrder> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键 id 查询
     *
     * @param id
     * @return
     */
    TbOrder selectByPrimaryKey(String id);


    /**
     * 获取订单总数
     *
     * @return
     */
    int getAllOrderCount();

    /**
     * 获取订单列表
     *
     * @param params 参数
     * @return
     */
    List<TbOrder> getOrderList(Map<String, Object> params);

    /**
     * 获取订单列表数量
     *
     * @param params 参数
     * @return
     */
    int getTbOrderCount(Map<String, Object> params);


    /**
     * 查询图表数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<OrderChartData> selectOrderChart(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 按年份查询图表数据
     *
     * @param year 年份
     * @return
     */
    List<OrderChartData> selectOrderChartByYear(int year);
}