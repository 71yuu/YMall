package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbItemCat;

import java.util.List;

public interface TbItemCatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItemCat record);

    TbItemCat selectByPrimaryKey(Long id);

    List<TbItemCat> selectAll();

    int updateByPrimaryKey(TbItemCat record);

    /**
     * 根据分级分类，获取子分类
     *
     * @param id 分类 id
     * @return
     */
    List<TbItemCat> selectTbCatByPid(Long id);
}
