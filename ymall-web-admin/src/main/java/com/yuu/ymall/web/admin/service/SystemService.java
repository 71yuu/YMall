package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.domain.TbBase;
import com.yuu.ymall.domain.TbLog;
import com.yuu.ymall.domain.TbOrderItem;
import com.yuu.ymall.domain.TbShiroFilter;

import java.util.List;

/**
 * @Classname SystemService
 * @Date 2019/5/11 20:21
 * @Created by Yuu
 */
public interface SystemService {

    /**
     * 获取动态权限
     *
     * @return
     */
    List<TbShiroFilter> getShiroFilter();

    /**
     * 获取系统基本配置
     *
     * @return
     */
    TbBase getBase();

    /**
     * 添加日志
     *
     * @param tbLog 日志
     */
    int addLog(TbLog tbLog);

    /**
     * 获取本周热销商品
     *
     * @return
     */
    TbOrderItem getWeekHot();
}
