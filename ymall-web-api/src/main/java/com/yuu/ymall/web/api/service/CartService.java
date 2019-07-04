package com.yuu.ymall.web.api.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.api.dto.Cart;

/**
 * @author by Yuu
 * @classname CartService
 * @date 2019/7/3 9:13
 */
public interface CartService {

    /**
     * 购物车添加商品
     *
     * @param cart 购物车
     * @return
     */
    BaseResult addProduct(Cart cart);

    /**
     * 获取购物车列表
     *
     * @param userId 会员 id
     * @return
     */
    BaseResult getCartList(Long userId);

    /**
     * 购物车删除商品
     *
     * @param cart 购物车
     * @return
     */
    BaseResult delProduct(Cart cart);
}
