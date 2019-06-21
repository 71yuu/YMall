package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbOrderShipping;

public interface TbOrderShippingMapper extends BaseMapper<TbOrderShipping> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键 id 查询
     *
     * @param id
     * @return
     */
    TbOrderShipping selectByPrimaryKey(String id);
}