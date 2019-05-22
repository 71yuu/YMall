package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbOrder;

public interface TbOrderMapper extends BaseMapper<TbOrder> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 获取订单总数
     *
     * @return
     */
    int getAllOrderCount();
}