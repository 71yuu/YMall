package com.yuu.ymall.web.api.service;

import com.yuu.ymall.commons.dto.BaseResult;

/**
 * @author by Yuu
 * @classname ContentService
 * @date 2019/7/2 13:51
 */
public interface ContentService {

    /**
     * 获取首页板块内容
     *
     * @return
     */
    BaseResult getHome();

    /**
     * 获取推荐商品
     *
     * @return
     */
    BaseResult getRecommendGoods();
}
