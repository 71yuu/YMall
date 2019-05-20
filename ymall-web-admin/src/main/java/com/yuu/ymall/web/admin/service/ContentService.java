package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbPanelContent;
import com.yuu.ymall.web.admin.commons.dto.PageInfo;

/**
 * @Classname ContentService
 * @Date 2019/5/16 23:52
 * @Created by Yuu
 */
public interface ContentService {

    /**
     * 通过 panelId 获取板块具体内容
     *
     * @param panelId 板块 ID
     * @return
     */
    PageInfo<TbPanelContent> getPanelContentListByPanelId(int panelId);

    /**
     * 编辑板块内容
     *
     * @param tbPanelContent 编辑板块内容
     * @return
     */
    BaseResult updateContent(TbPanelContent tbPanelContent);

    /**
     * 删除板块内容
     *
     * @param id 板块内容 id 集合
     * @return
     */
    BaseResult deletePanelContent(int[] ids);

    /**
     * 添加板块内容
     *
     * @param tbPanelContent 板块内容
     * @return
     */
    BaseResult addPanelContent(TbPanelContent tbPanelContent);
}
