package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbShiroFilter;

import java.util.List;

public interface TbShiroFilterMapper extends BaseMapper<TbShiroFilter> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 获取所有 Shiro 权限过滤器
     *
     * @return
     */
    List<TbShiroFilter> getShiroFilter();
}