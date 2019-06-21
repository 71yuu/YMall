package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbExpress;

import java.util.List;
import java.util.Map;

public interface TbExpressMapper extends BaseMapper<TbExpress> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 获取快递列表
     *
     * @param params 参数
     * @return
     */
    List<TbExpress> getExpressList(Map<String, Object> params);

    /**
     * 获取快递总数
     *
     * @param params 参数
     * @return
     */
    int getTbExpressCount(Map<String, Object> params);
}