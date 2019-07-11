package com.yuu.ymall.web.api.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.api.dto.Cart;
import com.yuu.ymall.web.api.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车视图层
 *
 * @author by Yuu
 * @classname CartController
 * @date 2019/7/3 9:08
 */
@RestController
@RequestMapping("member/cart")
@Api(description = "购物车服务接口")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     *
     * @param cart 商品
     * @return
     */
    @PostMapping("addProduct")
    @ApiOperation(value = "购物车添加商品")
    public BaseResult addProduct(@RequestBody Cart cart) {
        BaseResult baseResult = cartService.addProduct(cart);
        return baseResult;
    }

    /**
     * 获取会员购物车列表
     *
     * @param userId 会员 id
     * @return
     */
    @GetMapping("getCartList")
    @ApiOperation(value = "获取购物车列表")
    public BaseResult getCartList(@RequestParam Long userId) {
        BaseResult baseResult = cartService.getCartList(userId);
        return baseResult;
    }

    /**
     * 删除购物车商品
     *
     * @param userId 会员id
     * @param productId 商品id
     * @return
     */
    @PostMapping("delProduct")
    @ApiOperation(value = "购物车删除商品")
    public BaseResult delProduct(@RequestBody Cart cart) {
        BaseResult baseResult = cartService.delProduct(cart);
        return baseResult;
    }

    /**
     * 修改购物车商品
     *
     * @param cart 购物车商品
     * @return
     */
    @PostMapping("editProduct")
    @ApiOperation(value = "修改购物车商品")
    public BaseResult editProduct(@RequestBody Cart cart) {
        BaseResult baseResult = cartService.editProduct(cart);
        return baseResult;
    }

    /**
     * 全选购物车
     *
     * @param cart 购物车
     * @return
     */
    @PostMapping("editCheckAll")
    @ApiOperation(value = "全选购物车")
    public BaseResult editCheckAll(@RequestBody Cart cart) {
        BaseResult baseResult = cartService.editCheckAll(cart);
        return baseResult;
    }

    /**
     * 删除已选购物车商品
     *
     * @param cart 购物车
     * @return
     */
    @PostMapping("delChecked")
    @ApiOperation(value = "删除已选购物车商品")
    public BaseResult delCartChecked(@RequestBody Cart cart) {
        BaseResult baseResult = cartService.delCartChecked(cart);
        return baseResult;
    }

}

