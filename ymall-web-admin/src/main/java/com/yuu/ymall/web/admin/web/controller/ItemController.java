package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.web.admin.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
