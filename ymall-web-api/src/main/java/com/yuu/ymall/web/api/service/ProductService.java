package com.yuu.ymall.web.api.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.api.dto.CategoryProductPageInfo;

/**
 * @author by Yuu
 * @classname ProductService
 * @date 2019/7/3 18:51
 */
public interface ProductService {

    /**
     * 获取商品详情
     *
     * @param productId 商品 id
     * @return
     */
    BaseResult getProductDet(Long productId);

    /**
     * 获取分类商品
     *
     * @param categoryProductPageInfo 分类商品查询信息
     * @return
     */
    BaseResult getByCategory(CategoryProductPageInfo categoryProductPageInfo);

    /**
     * 快速搜索
     *
     * @param key 关键字
     * @return
     */
    BaseResult getQuickSearch(String key);
}
