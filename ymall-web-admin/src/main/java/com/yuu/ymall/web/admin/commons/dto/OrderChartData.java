package com.yuu.ymall.web.admin.commons.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单统计数据传输对象
 *
 * @author by Yuu
 * @classname OrderChartData
 * @date 2019/6/21 11:31
 */
public class OrderChartData implements Serializable {

    /**
     * 日期
     */
    Date time;

    /**
     * 总销量
     */
    BigDecimal money;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
