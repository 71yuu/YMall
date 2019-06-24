package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbPanel;

import java.util.List;

public interface TbPanelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbPanel record);

    TbPanel selectByPrimaryKey(Integer id);

    List<TbPanel> selectAll();

    int updateByPrimaryKey(TbPanel record);
}