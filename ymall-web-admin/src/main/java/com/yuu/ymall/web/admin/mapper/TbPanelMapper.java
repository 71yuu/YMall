package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbPanel;

import java.util.List;
import java.util.Map;

public interface TbPanelMapper extends BaseMapper<TbPanel> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据条件查询所有板块
     *
     * @param params
     * @return
     */
    List<TbPanel> getPanelList(Map<String, Object> params);

    /**
     * 根据板块的类型查询板块
     *
     * @param type
     * @return
     */
    TbPanel getPanelByType(int type);
}