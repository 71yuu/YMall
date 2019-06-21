package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbPanelContent;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ContentService
 * @Date 2019/5/16 23:52
 * @Created by Yuu
 */
public interface ContentService {


    /**
     * 通过板块 id 分页查询板块内容
     *
     * @param request 请求
     * @param panelId 板块 id
     * @param search 搜索条件
     * @return
     */
    DataTablesResult<TbPanelContent> getPanelContentListByPanelId(HttpServletRequest request, int panelId, String search);

    /**
     * 删除板块内容
     *
     * @param id 板块内容 id 集合
     * @return
     */
    BaseResult deletePanelContent(int[] ids);

    /**
     * 新增或编辑板块内容
     *
     * @param tbPanelContent 板块内容
     * @return
     */
    BaseResult saveContent(TbPanelContent tbPanelContent);

    /**
     * 查询商品是否关联首页板块内容
     *
     * @param id 商品 id
     */
    int selectContentByIid(Long id);
}
