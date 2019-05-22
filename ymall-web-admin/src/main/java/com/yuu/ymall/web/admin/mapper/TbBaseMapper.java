package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbBase;

public interface TbBaseMapper extends BaseMapper<TbBase> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 获取系统基本信息
     *
     * @return
     */
    TbBase getBase();
}