package com.yuu.ymall.web.admin.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.utils.TimeUtil;
import com.yuu.ymall.web.admin.commons.consts.Consts;
import com.yuu.ymall.web.admin.commons.dto.ChartData;
import com.yuu.ymall.web.admin.commons.dto.OrderChartData;
import com.yuu.ymall.web.admin.mapper.TbOrderMapper;
import com.yuu.ymall.web.admin.service.CountService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author by Yuu
 * @classname CountServiceImpl
 * @date 2019/6/21 11:03
 */
@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public BaseResult countOrder(int type, String startTime, String endTime, int year) {
        ChartData chartData = new ChartData();
        Date startDate = null, endDate = null;
        if (type == Consts.CUSTOM_DATE) {
            startDate = DateUtil.beginOfDay(DateUtil.parse(startTime));
            endDate = DateUtil.endOfDay(DateUtil.parse(endTime));
            long betweenDay = DateUtil.between(startDate, endDate, DateUnit.DAY);
            if (betweenDay > 31) {
                return BaseResult.fail("所选日期范围过长，最多不能超过31天");
            }
        }
        List<OrderChartData> list = getOrderCountData(type, startDate, endDate, year);
        List<Object> xDatas = new ArrayList<>();
        List<Object> yDatas = new ArrayList<>();
        BigDecimal countAll = new BigDecimal("0");
        for (OrderChartData orderChartData : list) {
            if (type == Consts.CUSTOM_YEAR) {
                xDatas.add(DateUtil.format(orderChartData.getTime(), "yyyy-MM"));
            } else {
                xDatas.add(DateUtil.formatDate(orderChartData.getTime()));
            }
            yDatas.add(orderChartData.getMoney());
            countAll = countAll.add(orderChartData.getMoney());
        }
        chartData.setxDatas(xDatas);
        chartData.setyDatas(yDatas);
        chartData.setCountAll(countAll);
        return BaseResult.success(chartData);
    }

    private List<OrderChartData> getOrderCountData(int type, Date startTime, Date endTime, int year) {
        List<OrderChartData> fullData = new ArrayList<>();

        List<OrderChartData> data;

        switch (type) {
            // 本周
            case 0:
                data = tbOrderMapper.selectOrderChart(TimeUtil.getBeginDayOfWeek(), TimeUtil.getEndDayOfWeek());
                fullData = getFullData(data, TimeUtil.getBeginDayOfWeek(), TimeUtil.getEndDayOfWeek());
                break;
            // 本月
            case 1:
                data = tbOrderMapper.selectOrderChart(TimeUtil.getBeginDayOfMonth(), TimeUtil.getEndDayOfMonth());
                fullData = getFullData(data, TimeUtil.getBeginDayOfMonth(), TimeUtil.getEndDayOfMonth());
                break;
            // 上月
            case 2:
                data = tbOrderMapper.selectOrderChart(TimeUtil.getBeginDayOfLastMonth(), TimeUtil.getEndDayOfLastMonth());
                fullData = getFullData(data, TimeUtil.getBeginDayOfLastMonth(), TimeUtil.getEndDayOfLastMonth());
                break;
            // 自定义
            case -1:
                data = tbOrderMapper.selectOrderChart(startTime, endTime);
                fullData = getFullData(data, startTime, endTime);
                break;
            // 按年份
            case -2:
                data = tbOrderMapper.selectOrderChartByYear(year);
                fullData = getFullYearData(data, year);
                break;
        }
        return fullData;
    }

    /**
     * 无数据补0
     *
     * @param data      数据
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    private List<OrderChartData> getFullData(List<OrderChartData> data, Date startTime, Date endTime) {
        List<OrderChartData> fullData = new ArrayList<>();

        // 相差
        long betweenDay = DateUtil.between(startTime, endTime, DateUnit.DAY);

        // 起始时间
        Date everyday = startTime;

        int count = -1;
        for (int i = 0; i <= betweenDay; i++) {
            boolean flag = true;
            for (OrderChartData chartData : data) {
                if (DateUtils.isSameDay(chartData.getTime(), everyday)) {
                    // 有数据
                    flag = false;
                    count++;
                    break;
                }
            }
            if (!flag) {
                fullData.add(data.get(count));
            } else {
                OrderChartData orderChartData = new OrderChartData();
                orderChartData.setTime(everyday);
                orderChartData.setMoney(new BigDecimal("0"));
                fullData.add(orderChartData);
            }

            // 时间 +1 天
            Calendar cal = Calendar.getInstance();
            cal.setTime(everyday);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            everyday = cal.getTime();
        }
        return fullData;
    }

    /**
     * 无数据补0
     *
     * @param data 数据
     * @param year 年份
     * @return
     */
    public List<OrderChartData> getFullYearData(List<OrderChartData> data, int year) {

        List<OrderChartData> fullData = new ArrayList<>();
        //起始月份
        Date everyMonth = TimeUtil.getBeginDayOfYear(year);
        int count = -1;
        for (int i = 0; i < 12; i++) {
            boolean flag = true;
            for (OrderChartData chartData : data) {
                if (DateUtil.month(chartData.getTime()) == DateUtil.month(everyMonth)) {
                    //有数据
                    flag = false;
                    count++;
                    break;
                }
            }
            if (!flag) {
                fullData.add(data.get(count));
            } else {
                OrderChartData orderChartData = new OrderChartData();
                orderChartData.setTime(everyMonth);
                orderChartData.setMoney(new BigDecimal("0"));
                fullData.add(orderChartData);
            }

            //时间+1天
            Calendar cal = Calendar.getInstance();
            cal.setTime(everyMonth);
            cal.add(Calendar.MONTH, 1);
            everyMonth = cal.getTime();
        }
        return fullData;
    }
}
