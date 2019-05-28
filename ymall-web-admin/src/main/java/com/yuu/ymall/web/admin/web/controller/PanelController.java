package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbPanel;
import com.yuu.ymall.web.admin.commons.dto.ZTreeNode;
import com.yuu.ymall.web.admin.service.PanelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname PanelController
 * @Date 2019/5/20 8:46
 * @Created by Yuu
 */
@RestController
@Api(description = "内容板块管理")
@RequestMapping("panel")
public class PanelController {

    private final static Logger log = LoggerFactory.getLogger(PanelController.class);

    @Autowired
    private PanelService panelService;

    /**
     * 通过的获取板块内容
     * type: 0 仅含轮播，-1 非轮播，1 所有
     *
     * @param type 类型
     * @param showAll
     * @return
     */
    @GetMapping("common/list/{type}")
    @ApiOperation(value = "通用的获取板块内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "int", paramType = "path"),
    })
    public List<ZTreeNode> getCommonPanel(@PathVariable int type) {
        List<ZTreeNode> zTreeNodeList = panelService.getPanelList(type);
        return zTreeNodeList;
    }

    /**
     * 编辑板块
     *
     * @param tbPanel 板块信息
     * @return
     */
    @PostMapping("update")
    @ApiOperation(value = "编辑内容板块")
    public BaseResult updateContentPanel(@ModelAttribute TbPanel tbPanel) {
        BaseResult baseResult = panelService.updatePanel(tbPanel);
        return baseResult;
    }

    /**
     * 删除内容板块
     *
     * @param id 板块 id
     * @return
     */
    @DeleteMapping("del/{id}")
    @ApiOperation(value = "删除内容板块")
    public BaseResult deleteContentPanel(@PathVariable int id) {
        BaseResult baseResult = panelService.deletePanelById(id);
        return baseResult;
    }

    /**
     * 添加内容板块
     *
     * @param tbPanel 板块
     * @return
     */
    @PostMapping("add")
    @ApiOperation(value = "添加内容板块")
    public BaseResult addContentPanel(@ModelAttribute TbPanel tbPanel) {
        BaseResult baseResult = panelService.addPanel(tbPanel);
        return baseResult;
    }


}
