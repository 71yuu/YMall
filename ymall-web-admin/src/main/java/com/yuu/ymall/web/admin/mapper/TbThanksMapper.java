package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbThanks;

public interface TbThanksMapper extends BaseMapper<TbThanks> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
}