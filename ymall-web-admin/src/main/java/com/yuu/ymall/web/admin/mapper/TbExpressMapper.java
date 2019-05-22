package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbExpress;

public interface TbExpressMapper extends BaseMapper<TbExpress> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
}