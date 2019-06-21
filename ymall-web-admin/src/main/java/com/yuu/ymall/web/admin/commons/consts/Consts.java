package com.yuu.ymall.web.admin.commons.consts;

/**
 * 常量类
 *
 * @Classname Consts
 * @Date 2019/6/15 20:13
 * @Created by Yuu
 */
public class Consts {

    /**
     * ####### 订单状态
     */

    /**
     * 订单未付款
     */
    public static final Integer ORDER_STATE_UNPAID = 0;
    
    /**
     * 订单已付款
     */
    public static final Integer ORDER_STATE_PAID = 1;
    
    /**
     * 订单未发货
     */
    public static final Integer ORDER_STATE_UNSHIPPED = 2;
 
    /**
     * 订单已发货
     */
    public static final Integer ORDER_STATE_SHIPPED = 3;
 
    /**
     * 订单交易成功
     */
    public static final Integer ORDER_STATE_SUCCESS = 4;
 
    /**
     * 订单交易关闭
     */
    public static final Integer ORDER_STATE_CLOSE = 5;

    /**
     * 交易失败
     */
    public static final Integer ORDER_STATE_FAILED = 6;


    /**
     * ######### 会员状态
     */

    /**
     * 会员启用，正常状态
     */
    public static final Integer MEMBER_START = 1;

    /**
     * 会员被封禁
     */
    public static final Integer MEMBER_BAN = 2;


    /**
     * ######### 报表统计
     */

    /**
     * 自定义查询报表
     */
    public static final Integer CUSTOM_DATE = -1;

    /**
     * 按年统计
     */
    public static final Integer CUSTOM_YEAR = -2;

    /**
     * 本周
     */
    public static final Integer THIS_WEEK = 0;

    /**
     * 本月
     */
    public static final Integer THIS_MONTH = 1;

    /**
     * 上个月
     */
    public static final Integer LAST_MONTH = 2;
}
