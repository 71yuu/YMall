package com.yuu.ymall.web.api.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.api.dto.CategoryProductPageInfo;
import com.yuu.ymall.web.api.repositories.ItemRepository;
import com.yuu.ymall.web.api.service.ContentService;
import com.yuu.ymall.web.api.service.ItemCatService;
import com.yuu.ymall.web.api.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Yuu
 * @classname GoodsController
 * @date 2019/6/29 18:03
 */
@RestController
@RequestMapping("goods")
@Api(description = "商品服务接口")
public class GoodsController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemCatService itemCatService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ProductService productService;

    /**
     * 快速搜索
     *
     * @param key 搜索关键字
     * @return
     */
    @GetMapping(value = "quickSearch")
    @ApiOperation("")
    public BaseResult getQuickSearch(@RequestParam(defaultValue = "") String key) {
        BaseResult baseResult = productService.getQuickSearch(key);
        return BaseResult.success(baseResult);
    }

    /**
     * 获取分类信息
     *
     * @return
     */
    @GetMapping("cateList")
    @ApiOperation(value = "获取分类列表")
    public BaseResult getCateList() {
        BaseResult baseResult = itemCatService.getCateList();
        return baseResult;
    }

    /**
     * 获取首页各板块内容
     *
     * @return
     */
    @GetMapping("home")
    @ApiOperation(value = "获取首页各板块内容")
    public BaseResult getProductHome() {
        BaseResult baseResult = contentService.getHome();
        return baseResult;
    }

    /**
     * 获取商品详情
     *
     * @param productId 商品 id
     * @return
     */
    @GetMapping("productDet")
    @ApiOperation(value = "获取商品详情")
    public BaseResult getProductDet(@RequestParam Long productId) {
        BaseResult baseResult = productService.getProductDet(productId);
        return baseResult;
    }

    /**
     * 获取推荐商品模块
     *
     * @return
     */
    @GetMapping("recommend")
    @ApiOperation(value = "获取推荐商品")
    public BaseResult getRecommendGoods() {
        BaseResult baseResult = contentService.getRecommendGoods();
        return baseResult;
    }

    /**
     * 分页商品查询
     *
     * @param categoryProductPageInfo
     * @return
     */
    @GetMapping("getCategoryGoods")
    @ApiOperation(value = "所有商品")
    public BaseResult getCategoryProducts(CategoryProductPageInfo categoryProductPageInfo) {
        BaseResult baseResult = productService.getByCategory(categoryProductPageInfo);
        return baseResult;
    }
}

