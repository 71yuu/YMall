package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbPanelContent;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ContentController
 * @Date 2019/5/16 23:51
 * @Created by Yuu
 */
@RestController
@Api(description = "内容列表信息")
@RequestMapping("content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 通过 panelId 获取板块内容列表
     *
     * @param panelId 板块 id
     * @return
     */
    @GetMapping("list/{panelId}")
    @ApiOperation("通过 panelId 获取板块内容列表")
    public DataTablesResult<TbPanelContent> getContentByCid(@PathVariable int panelId, HttpServletRequest request, @RequestParam(value = "search[value]", required = false) String search) {
         DataTablesResult<TbPanelContent> result = contentService.getPanelContentListByPanelId(request, panelId, search);
         return result;
    }

    /**
     * 新增或编辑内容
     * 有 id 则为编辑，无 id 则为 新增
     *
     * @param tbPanelContent
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "新增或编辑内容")
    public BaseResult saveContent(@ModelAttribute TbPanelContent tbPanelContent) {
        BaseResult baseResult = contentService.saveContent(tbPanelContent);
        return baseResult;
    }

    /**
     * 删除板块内容
     *
     * @param ids 板块内容的 id 数组
     * @return
     */
    @DeleteMapping("delete/{ids}")
    @ApiOperation(value = "删除板块内容")
    public BaseResult deleteContent(@PathVariable int[] ids) {
        BaseResult baseResult = contentService.deletePanelContent(ids);
        return baseResult;
    }

}
