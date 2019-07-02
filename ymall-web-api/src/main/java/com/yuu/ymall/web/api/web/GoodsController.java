package com.yuu.ymall.web.api.web;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.api.domain.ESItem;
import com.yuu.ymall.web.api.repositories.ItemRepository;
import com.yuu.ymall.web.api.service.ContentService;
import com.yuu.ymall.web.api.service.ItemCatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author by Yuu
 * @classname GoodsController
 * @date 2019/6/29 18:03
 */
@RestController
@RequestMapping("goods")
@Api(description = "商品页面展示")
public class GoodsController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemCatService itemCatService;

    @Autowired
    private ContentService contentService;

    /**
     * 快速搜索
     *
     * @param key 搜索关键字
     * @return
     */
    @GetMapping(value = "quickSearch")
    @ApiOperation("")
    public BaseResult getQuickSearch(@RequestParam(defaultValue = "") String key) {
        // 构建查询条件
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        // 添加基本查询条件
        searchQueryBuilder.withQuery(QueryBuilders.termQuery("title", key));
        // 初始化分页参数
        int page = 0;
        int size = 5;
        // 设置分页参数
        searchQueryBuilder.withPageable(PageRequest.of(page, size));
        Page<ESItem> searchs = itemRepository.search(searchQueryBuilder.build());
        List<ESItem> esItems = searchs.getContent();
        return BaseResult.success(esItems);
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
}
