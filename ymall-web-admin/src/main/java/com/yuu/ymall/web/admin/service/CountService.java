package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;

/**
 * @author by Yuu
 * @classname CountService
 * @date 2019/6/21 11:03
 */
public interface CountService {

    /**
     * 订单销量统计
     *
     * @param type 类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param year 年份
     * @return
     */
    BaseResult countOrder(int type, String startTime, String endTime, int year);
}
