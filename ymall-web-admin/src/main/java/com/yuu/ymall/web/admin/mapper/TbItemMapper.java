package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbItem;

public interface TbItemMapper extends BaseMapper<TbItem> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 获取商品总数
     *
     * @return
     */
    int getAllItemCount();
}