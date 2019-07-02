package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbPanelContent;
import com.yuu.ymall.web.api.dto.TbPanelContentDto;

import java.util.List;

public interface TbPanelContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbPanelContent record);

    TbPanelContent selectByPrimaryKey(Integer id);

    List<TbPanelContent> selectAll();

    int updateByPrimaryKey(TbPanelContent record);

    /**
     * 根据板块 id, 获取板块内容
     *
     * @param id 板块 id
     * @return
     */
    List<TbPanelContentDto> selectContentByPid(Integer id);
}
