package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.commons.dto.ItemDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ItemService
 * @Date 2019/5/15 22:28
 * @Created by Yuu
 */
public interface ItemService {

    /**
     * 获取商品总数
     * @return
     */
    int getAllItemCount();


    /**
     * 分页搜索排序获取商品列表
     *
     * @param request 请求
     * @param cid 分类 id
     * @param search 查询条件
     * @return
     */
    DataTablesResult<TbItem> getItemListByCid(HttpServletRequest request, Long cid, String search);

    /**
     * 根据商品 id 集合，删除商品
     *
     * @param ids 商品 id 集合
     * @return
     */
    BaseResult deleteItem(Long[] ids);

    /**
     * 根据商品 id 下架商品
     *
     * @param id 商品 id
     * @return
     */
    BaseResult stopItem(Long id);

    /**
     * 根据商品 id 发布商品
     *
     * @param id 商品 id
     * @return
     */
    BaseResult startItem(Long id);

    /**
     * 保存商品：有 id 编辑商品，无 id 新增商品
     *
     * @param itemDto 商品 DTO
     * @return
     */
    BaseResult saveItem(ItemDto itemDto);

    /**
     * 根据商品 ID 获取商品
     *
     * @param itemId 商品 ID
     * @return
     */
    BaseResult getItemById(Long itemId);
}
