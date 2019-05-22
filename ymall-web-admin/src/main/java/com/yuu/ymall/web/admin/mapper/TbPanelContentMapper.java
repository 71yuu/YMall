package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbPanelContent;

import java.util.List;

public interface TbPanelContentMapper extends BaseMapper<TbPanelContent> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据板块 id 获取板块内容
     *
     * @param panelId 板块 id
     * @return
     */
    List<TbPanelContent> getTbPanlContentByPanelId(int panelId);
}