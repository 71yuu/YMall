package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbPanelContent;

import java.util.List;

public interface TbPanelContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbPanelContent record);

    TbPanelContent selectByPrimaryKey(Integer id);

    List<TbPanelContent> selectAll();

    int updateByPrimaryKey(TbPanelContent record);

    List<TbPanelContent> getTbPanlContentByPanelId(int panelId);
}