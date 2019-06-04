package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbItemCat;

import java.util.List;

public interface TbItemCatMapper extends BaseMapper<TbItemCat> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据父 ID 查询分类列表
     *
     * @param parentId 父 id
     * @return
     */
    List<TbItemCat> getItemCatList(Long parentId);

    /**
     * 查询该分类最大的排序值
     *
     * @param parentId 父 id
     * @return
     */
    int getMaxSortOrder(Long parentId);
}