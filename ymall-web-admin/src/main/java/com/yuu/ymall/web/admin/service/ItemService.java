package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;

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
}
