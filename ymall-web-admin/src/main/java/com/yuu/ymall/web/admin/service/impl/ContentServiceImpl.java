package com.yuu.ymall.web.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.domain.TbPanelContent;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.mapper.TbItemMapper;
import com.yuu.ymall.web.admin.mapper.TbPanelContentMapper;
import com.yuu.ymall.web.admin.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 首页商品缓存 key
     */
    @Value("PRODUCT_HOME")
    private String PRODUCT_HOME;

    @Value("0")
    private int HEADER_PANEL_ID;

    @Value("HEADER_PANEL")
    private String HEADER_PANEL;

    @Override
    public DataTablesResult<TbPanelContent> getPanelContentListByPanelId(HttpServletRequest request, int panelId, String search) {
        DataTablesResult<TbPanelContent> result = new DataTablesResult<>(request);

        Map<String, Object> params = new HashMap<>();
        params.put("panelId", panelId);
        params.put("search", search);

        // 分页查询
        PageHelper.startPage(result.getPageNum(), result.getLength());
        List<TbPanelContent> tbPanelContentList = tbPanelContentMapper.getTbPanelContentByPanelId(params);

        // 封装内容
        for (TbPanelContent tbPanelContent : tbPanelContentList) {
            if (tbPanelContent.getProductId() != null) {
                TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbPanelContent.getProductId());
                tbPanelContent.setProductName(tbItem.getTitle());
                tbPanelContent.setSalePrice(tbItem.getPrice());
                tbPanelContent.setSubTitle(tbItem.getSellPoint());
            }
        }

        PageInfo<TbPanelContent> tbPanelContentPageInfo = new PageInfo<>(tbPanelContentList);
        result.setRecordsFiltered((int) tbPanelContentPageInfo.getTotal());
        result.setRecordsTotal(tbPanelContentMapper.getTbPanelContentCount(params));
        result.setDraw(result.getDraw());
        result.setData(tbPanelContentList);

        return result;
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
    public BaseResult saveContent(TbPanelContent tbPanelContent) {
        tbPanelContent.setUpdated(new Date());

        int result;

        // 新增
        if (tbPanelContent.getId() == null) {
            tbPanelContent.setCreated(new Date());
            result = tbPanelContentMapper.insert(tbPanelContent);

            if (result == 0) {
                return BaseResult.fail("添加导航栏失败！");
            }
        }

        // 编辑
        else {
            result = tbPanelContentMapper.updateByPrimaryKey(tbPanelContent);

            // 更新失败
            if (result != 1) {
                return BaseResult.fail("更新板块内容失败！");
            }
        }

        // 删除导航栏缓存
        if (tbPanelContent.getPanelId() == HEADER_PANEL_ID) {
            updateNavListRedis();
        }

        // 删除首页缓存
        deleteHomeRedis();
        return BaseResult.success("保存导航栏成功！");
    }

    @Override
    public int selectContentByIid(Long id) {
        return tbPanelContentMapper.selectContentByIid(id);
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
