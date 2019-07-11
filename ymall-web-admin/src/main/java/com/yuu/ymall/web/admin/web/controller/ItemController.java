package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.commons.dto.ItemDto;
import com.yuu.ymall.web.admin.mapper.TbOrderItemMapper;
import com.yuu.ymall.web.admin.service.ContentService;
import com.yuu.ymall.web.admin.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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


    @Autowired
    private ItemService itemService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

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
     * @param cid 分类 id
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

    /**
     * 删除商品
     *
     * @param ids 商品 id 集合
     * @return
     */
    @DeleteMapping("delete/{ids}")
    @ApiOperation(value = "删除商品")
    public BaseResult deleteItem(@PathVariable Long[] ids) {
        // 判断首页是否关联
        for (Long id : ids) {
            int result = contentService.selectContentByIid(id);
            if (result > 0) {
                return BaseResult.fail("删除商品失败！包含首页展示关联的商品,商品ID："+ id +"，请先从首页配置中删除该商品关联");
            }
            // 判断商品是否已有订单，有订单则不能删除
            result = tbOrderItemMapper.selectByItemId(id);
            if (result > 0) {
                return BaseResult.fail("删除商品失败！包含已有订单的商品,商品ID："+ id +"，请使用下架处理该商品！");
            }
        }
        BaseResult baseResult = itemService.deleteItem(ids);
        return baseResult;
    }

    /**
     * 下架商品
     *
     * @param id 商品 id
     * @return
     */
    @DeleteMapping("stop/{id}")
    @ApiOperation(value = "下架商品")
    public BaseResult stopItem(@PathVariable Long id) {
        // 判断首页是否关联
        int result = contentService.selectContentByIid(id);
        if (result > 0) {
            return BaseResult.fail("下架商品失败！包含首页展示关联的商品,商品ID："+ id +"，请先从首页配置中删除该商品关联");
        }
        BaseResult baseResult = itemService.stopItem(id);
        return baseResult;
    }

    /**
     * 发布商品
     *
     * @param id 商品 id
     * @return
     */
    @DeleteMapping("start/{id}")
    @ApiOperation(value = "发布商品")
    public BaseResult startItem(@PathVariable Long id) {
        BaseResult baseResult = itemService.startItem(id);
        return baseResult;
    }

    /**
     * 保存商品：有 id 编辑商品，无 id 新增商品
     *
     * @param itemDto 商品 DTO
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "保存商品")
    public BaseResult saveItem(ItemDto itemDto) {
        BaseResult baseResult = itemService.saveItem(itemDto);
        // 更新索引库
        return baseResult;
    }

    /**
     * 根据商品 ID 获取商品
     *
     * @param itemId 商品 ID
     * @return
     */
    @GetMapping("{itemId}")
    @ApiOperation(value = "通过商品ID获取商品")
    public BaseResult getItemById(@PathVariable Long itemId) {
        BaseResult baseResult = itemService.getItemById(itemId);
        return baseResult;
    }

}
