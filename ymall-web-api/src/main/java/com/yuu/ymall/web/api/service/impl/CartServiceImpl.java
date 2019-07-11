package com.yuu.ymall.web.api.service.impl;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.commons.utils.MapperUtil;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.web.api.dto.Cart;
import com.yuu.ymall.web.api.dto.CartProduct;
import com.yuu.ymall.web.api.mapper.TbItemMapper;
import com.yuu.ymall.web.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author by Yuu
 * @classname CartServiceImpl
 * @date 2019/7/3 9:13
 */
@Service
public class CartServiceImpl implements CartService {

    @Value("${CART_PRE}")
    private String CART_PRE;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public BaseResult addProduct(Cart cart) {

        // Redis key CART:userId
        String cartKey = CART_PRE + ":" + cart.getUserId();

        // HASH key
        String hashKey = String.valueOf(cart.getProductId());

        // 判断缓存中该会员购物车中是否有该商品
        boolean hasHash = redisCacheManager.hasHashKye(cartKey, hashKey);
        // 有商品，数量相加
        if (hasHash) {
            try {
                String cartJson = (String) redisCacheManager.getHash(cartKey, hashKey);
                if (cartJson != null) {
                    CartProduct redisCartProduct = null;
                    redisCartProduct = MapperUtil.json2pojo(cartJson, CartProduct.class);
                    redisCartProduct.setProductNum(redisCartProduct.getProductNum() + cart.getProductNum());
                    String redisJson = MapperUtil.obj2json(redisCartProduct);
                    redisCacheManager.setHash(cartKey, hashKey, redisJson);
                    return BaseResult.success("添加商品到购物车成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 没有商品，新增一条
        try {
            TbItem tbItem = tbItemMapper.selectByPrimaryKey(cart.getProductId());
            if (tbItem == null) {
                return BaseResult.fail("要添加到的购物车的商品不存在");
            }
            CartProduct cartProduct = new CartProduct();
            cartProduct.setProductId(tbItem.getId());
            cartProduct.setProductName(tbItem.getTitle());
            cartProduct.setProductNum(cart.getProductNum());
            cartProduct.setProductImg(tbItem.getImages()[0]);
            cartProduct.setLimitNum(tbItem.getLimitNum());
            cartProduct.setSalePrice(tbItem.getPrice());
            cartProduct.setChecked("0");
            String redisJson = MapperUtil.obj2json(cartProduct);
            redisCacheManager.setHash(cartKey, hashKey, redisJson);
            return BaseResult.success("添加商品到购物车成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResult.fail("添加商品到购物车失败");
    }

    @Override
    public BaseResult getCartList(Long userId) {

        // Redis key
        String redisKey = CART_PRE + ":" + userId;

        // 获取会员所有购物车商品
        Map<String, Object> cartMap = redisCacheManager.getHashByKey(redisKey);

        // 封装成购物车集合
        List<CartProduct> cartProducts = new ArrayList<>();
        if (cartMap != null) {
            for (String key: cartMap.keySet()) {
                String productJson = (String) cartMap.get(key);
                CartProduct cartProduct = new CartProduct();
                try {
                    cartProduct = MapperUtil.json2pojo(productJson, CartProduct.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    return BaseResult.fail("获取购物车失败");
                }
                if (cartProduct != null) {
                    cartProducts.add(cartProduct);
                }
            }
        }

        return BaseResult.success(cartProducts);
    }

    @Override
    public BaseResult delProduct(Cart cart) {

        // Redis key
        String redisKey = CART_PRE + ":" + cart.getUserId();

        // Hash key
        String hashKey = String.valueOf(cart.getProductId());

        // 删除缓存
        redisCacheManager.deleteHash(redisKey, hashKey);

        return BaseResult.success("删除购物车商品成功");
    }

    @Override
    public BaseResult editProduct(Cart cart) {

        // Redis key
        String redisKey = CART_PRE + ":" + cart.getUserId();

        // Hash key
        String hashKey = String.valueOf(cart.getProductId());

        // 修改缓存
        try {
            String oldCartJson = (String) redisCacheManager.getHash(redisKey, hashKey);
            CartProduct oldCartProduct = MapperUtil.json2pojo(oldCartJson, CartProduct.class);
            oldCartProduct.setChecked(cart.getChecked());
            oldCartProduct.setProductNum(cart.getProductNum());
            String newCartProductJson = MapperUtil.obj2json(oldCartProduct);
            if (newCartProductJson != null) {
                redisCacheManager.setHash(redisKey, hashKey, newCartProductJson);
                return BaseResult.success("编辑购物车商品成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return BaseResult.fail("编辑购物车商品失败");
    }

    @Override
    public BaseResult editCheckAll(Cart cart) {

        // Redis key
        String redisKey = CART_PRE + ":" + cart.getUserId();

        Map<String, Object> hashByKey = redisCacheManager.getHashByKey(redisKey);
        for (String key : hashByKey.keySet()) {
            String oldCartProductJson = (String) hashByKey.get(key);
            try {
                CartProduct cartProduct = MapperUtil.json2pojo(oldCartProductJson, CartProduct.class);
                if (cartProduct != null) {
                    if ("true".equals(cart.getChecked())) {
                        cartProduct.setChecked("1");
                    } else {
                        cartProduct.setChecked("0");
                    }
                }
                String newCartProduct = MapperUtil.obj2json(cartProduct);
                redisCacheManager.setHash(redisKey, key, newCartProduct);
            } catch (Exception e) {
                e.printStackTrace();
                return BaseResult.fail("购物车全选失败");
            }
        }

        return BaseResult.success("购物车全选成功");
    }

    @Override
    public BaseResult delCartChecked(Cart cart) {

        // Redis key
        String redisKey = CART_PRE + ":" + cart.getUserId();

        Map<String, Object> hashByKey = redisCacheManager.getHashByKey(redisKey);
        for (String key : hashByKey.keySet()) {
            String oldCartProductJson = (String) hashByKey.get(key);
            try {
                CartProduct cartProduct = MapperUtil.json2pojo(oldCartProductJson, CartProduct.class);
                if (cartProduct != null && "1".equals(cartProduct.getChecked())) {
                    redisCacheManager.deleteHash(redisKey, key);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return BaseResult.fail("删除购物车已选商品失败");
            }
        }

        return BaseResult.success("删除购物车已选商品成功");
    }
}
