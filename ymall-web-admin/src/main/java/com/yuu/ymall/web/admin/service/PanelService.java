package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbPanel;
import com.yuu.ymall.web.admin.commons.dto.ZTreeNode;

import java.util.List;

/**
 * @Classname PanelService
 * @Date 2019/5/20 8:54
 * @Created by Yuu
 */
public interface PanelService {

    /**
     * 获取板块类目
     *
     * @param type
     * @return
     */
    List<ZTreeNode> getPanelList(int type);

    /**
     * 编辑内容板块
     *
     * @param tbPanel 板块
     * @return
     */
    BaseResult updatePanel(TbPanel tbPanel);

    /**
     * 根据板块 id 删除板块
     *
     * @param id 板块 id
     * @return
     */
    BaseResult deletePanelById(int id);

    /**
     * 新增内容板块
     *
     * @param tbPanel 板块
     * @return
     */
    BaseResult addPanel(TbPanel tbPanel);
}
