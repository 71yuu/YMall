package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.domain.TbPanelContent;
import com.yuu.ymall.web.admin.commons.dto.PageInfo;
import com.yuu.ymall.web.admin.commons.redis.RedisCacheManager;
import com.yuu.ymall.web.admin.mapper.TbItemMapper;
import com.yuu.ymall.web.admin.mapper.TbPanelContentMapper;
import com.yuu.ymall.web.admin.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Classname ContentServiceImpl
 * @Date 2019/5/16 23:52
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbPanelContentMapper tbPanelContentMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Value("PRODUCT_HOME")
    private String PRODUCT_HOME;

    @Value("0")
    private int HEADER_PANEL_ID;

    @Value("HEADER_PANEL")
    private String HEADER_PANEL;

    @Override
    public PageInfo<TbPanelContent> getPanelContentListByPanelId(int panelId) {

        // 获取板块内容结果集
        PageInfo<TbPanelContent> pageInfo = new PageInfo<>();
        List<TbPanelContent> tbPanelContentList = tbPanelContentMapper.getTbPanlContentByPanelId(panelId);

        // 封装内容
        for (TbPanelContent tbPanelContent : tbPanelContentList) {
            if (tbPanelContent.getProductId() != null) {
                TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbPanelContent.getProductId());
                tbPanelContent.setProductName(tbItem.getTitle());
                tbPanelContent.setSalePrice(tbItem.getPrice());
                tbPanelContent.setSubTitle(tbItem.getSellPoint());
            }
        }

        // 返回数据
        pageInfo.setData(tbPanelContentList);
        return pageInfo;
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult updateContent(TbPanelContent tbPanelContent) {
        // 更新板块内容
        tbPanelContent.setUpdated(new Date());
        int result = tbPanelContentMapper.updateByPrimaryKey(tbPanelContent);

        // 更新失败
        if (result != 1) {
            return BaseResult.fail("更新板块内容失败！");
        }

        // 删除导航栏缓存
        if (tbPanelContent.getPanelId() == HEADER_PANEL_ID) {
            updateNavListRedis();
        }

        // 同步缓存
        deleteHomeRedis();
        return BaseResult.success("更新板块内容成功");
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult deletePanelContent(int[] ids) {
        // 删除板块内容
        int result = 0;
        for (int id : ids) {
            result = tbPanelContentMapper.deleteByPrimaryKey(id);
            if (result != 1) {
                return BaseResult.fail("删除板块内容失败！");
            }
            // 同步导航栏缓存
            if (id == HEADER_PANEL_ID) {
                updateNavListRedis();
            }
        }
        // 同步缓存
        deleteHomeRedis();
        return BaseResult.success();
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult addPanelContent(TbPanelContent tbPanelContent) {
        tbPanelContent.setCreated(new Date());
        tbPanelContent.setUpdated(new Date());
        int result = tbPanelContentMapper.insert(tbPanelContent);

        if (result == 0) {
            return BaseResult.fail("添加导航栏失败！");
        }

        // 删除导航栏缓存
        if (tbPanelContent.getPanelId() == HEADER_PANEL_ID) {
            updateNavListRedis();
        }

        // 删除首页缓存
        deleteHomeRedis();
        return BaseResult.success("添加导航栏成功！");
    }

    /**
     * 删除导航栏缓存
     */
    private void updateNavListRedis() {
        redisCacheManager.del(HEADER_PANEL);
    }

    /**
     * 同步缓存
     */
    private void deleteHomeRedis() {
        redisCacheManager.del(PRODUCT_HOME);
    }
}
