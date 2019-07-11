package com.yuu.ymall.commons.consts;

/**
 * @author by Yuu
 * @classname Consts
 * @date 2019/6/24 16:52
 */
public class Consts {

    /**
     * ####### 会员状态 1 正常，2 封禁
     */

    /**
     * 会员状态正常
     */
    public static final Integer MEMBER_STATUS_NORMAL = 1;

    /**
     * 会员状态封禁
     */
    public static final Integer MEMBER_STATUS_BAN = 2;

    /**
     * ###### 订单状态 0 未支付，1 已支付，2 未发货，3 已发货，4 交易成功，5 交易关闭
     */

    /**
     * 未支付
     */
    public static final Integer ORDER_STATUS_NOPAY = 0;

    /**
     * 已支付
     */
    public static final Integer ORDER_STATUS_PAY = 1;

    /**
     * 未发货
     */
    public static final Integer ORDER_STATUS_NOSEND = 2;

    /**
     * 已发货
     */
    public static final Integer ORDER_STATUS_SEND = 3;

    /**
     * 交易成功
     */
    public static final Integer ORDER_STATUS_SUCCESS = 4;

    /**
     * 交易关闭
     */
    public static final Integer ORDER_STATUS_CLOSE = 5;
}
