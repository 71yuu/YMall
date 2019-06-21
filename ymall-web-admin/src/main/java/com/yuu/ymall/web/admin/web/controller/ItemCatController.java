package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbItemCat;
import com.yuu.ymall.web.admin.commons.dto.ZTreeNode;
import com.yuu.ymall.web.admin.service.ItemCatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname ItemCatController
 * @Date 2019/6/3 14:51
 * @Created by Yuu
 */
@RestController
@Api(description = "商品分类管理")
@RequestMapping("item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 通过父 ID 获取商品分类列表
     *
     * @param parentId 父 ID
     * @param type zTree 展示数据类型 -1 不展示所有商品，0 展示所有商品
     * @return
     */
    @GetMapping("list/{type}")
    @ApiOperation(value = "通过父 ID 获取商品分类列表")
    public List<ZTreeNode> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId, @PathVariable int type) {
        List<ZTreeNode> list = itemCatService.getItemCatList(parentId, type);
        return list;
    }

    /**
     * 编辑或添加商品分类
     *
     * @param tbItemCat 商品分类
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "编辑商品分类")
    public BaseResult saveItemCategory(@ModelAttribute TbItemCat tbItemCat) {
        BaseResult baseResult = itemCatService.saveItemCat(tbItemCat);
        return baseResult;
    }

    /**
     * 删除商品分类
     *
     * @param id 商品分类 id
     * @return
     */
    @DeleteMapping("del/{id}")
    @ApiOperation(value = "删除商品分类")
    public BaseResult deleteItemCategory(@PathVariable Long id) {
        BaseResult baseResult = itemCatService.deleteItemCat(id);
        return baseResult;
    }
}
