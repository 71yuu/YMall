package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbItemCat;
import com.yuu.ymall.web.admin.commons.dto.ZTreeNode;

import java.util.List;

/**
 * @Classname ItemCatService
 * @Date 2019/6/3 14:52
 * @Created by Yuu
 */
public interface ItemCatService {

    /**
     * 根据父类 id 查询分类列表
     *
     * @param parentId 根据父类 id 查询分类列表
     * @param type zTree 展示数据类型 -1 不展示所有商品，0 展示所有商品
     * @return
     */
    List<ZTreeNode> getItemCatList(Long parentId, int type);

    /**
     * 添加编辑商品分类
     *
     * @param tbItemCat 商品分类
     * @return
     */
    BaseResult saveItemCat(TbItemCat tbItemCat);

    /**
     * 删除商品分类
     *
     * @param id 分类 id
     * @return
     */
    BaseResult deleteItemCat(Long id);
}
