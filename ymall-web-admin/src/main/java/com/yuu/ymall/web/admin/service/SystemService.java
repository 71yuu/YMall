package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;

/**
 * @Classname SystemService
 * @Date 2019/5/11 20:21
 * @Created by Yuu
 */
public interface SystemService {

    /**
     * 获取本周热销商品
     *
     * @return
     */
    BaseResult getWeekHot();
}
