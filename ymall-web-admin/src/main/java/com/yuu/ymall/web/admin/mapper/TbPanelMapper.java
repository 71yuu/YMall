package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.domain.TbPanel;

import java.util.List;
import java.util.Map;

public interface TbPanelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbPanel record);

    TbPanel selectByPrimaryKey(Integer id);

    List<TbPanel> selectAll();

    int updateByPrimaryKey(TbPanel record);

    /**
     * 查询板块列表
     *
     * @param params 条件参数列表
     * @return
     */
    List<TbPanel> getPanelList(Map<String, String> params);

    /**
     * 根据板块类型获取板块
     *
     * @param type 板块类型
     * @return
     */
    TbPanel getPanelByType(int type);
}