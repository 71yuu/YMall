package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ItemController
 * @Date 2019/5/15 22:23
 * @Created by Yuu
 */
@RestController
@Api(description = "商品管理")
@RequestMapping("item")
public class ItemController {

    private final static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * 获取商品总数目
     *
     * @return
     */
    @GetMapping("count")
    @ApiOperation(value = "获取商品总数目")
    public BaseResult getAllItemCount() {
        int itemCount = itemService.getAllItemCount();
        return BaseResult.success(itemCount);
    }

    /**
     * 通过 Cid 获取商品列表
     *
     * @param  cid 分类 id
     * @param request 请求
     * @param search 查询条件
     * @return
     */
    @GetMapping("list/{cid}")
    @ApiOperation(value = "分页搜索排序获取商品列表")
    public DataTablesResult<TbItem> getItemList(@PathVariable Long cid, HttpServletRequest request, @RequestParam("search[value]") String search) {
        DataTablesResult<TbItem> result = itemService.getItemListByCid(request, cid, search);
        return result;
    }

}
