package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.domain.TbPanel;
import com.yuu.ymall.web.admin.commons.dto.ZTreeNode;
import com.yuu.ymall.web.admin.commons.utils.DtoUtil;
import com.yuu.ymall.web.admin.mapper.TbPanelMapper;
import com.yuu.ymall.web.admin.service.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Classname PanelServiceImpl
 * @Date 2019/5/20 8:54
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class PanelServiceImpl implements PanelService {

    @Autowired
    private TbPanelMapper tbPanelMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Value("PRODUCT_HOME")
    private String PRODUCT_HOME;

    @Override
    public List<ZTreeNode> getPanelList(int type) {
        Map<String, Object> params = new HashMap<>();

        // type = 1 时，查询所有，不需要 type 条件
        if (type != 1) {
            params.put("type", type);
        }

        // 首页板块
        params.put("position", "0");
        List<TbPanel> tbPanelList = tbPanelMapper.getPanelList(params);

        // 封装 ZTreeNode
        List<ZTreeNode> zTreeNodeList = new ArrayList<>();
        for (TbPanel tbPanel : tbPanelList) {
            ZTreeNode zTreeNode = DtoUtil.TbPanel2ZTreeNode(tbPanel);
            zTreeNodeList.add(zTreeNode);
        }

        return zTreeNodeList;
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult updatePanel(TbPanel tbPanel) {
        tbPanel.setUpdated(new Date());
        int result = tbPanelMapper.updateByPrimaryKey(tbPanel);

        // 更新失败
        if (result != 1) {
            return BaseResult.fail("更新板块失败");
        }

        // 同步缓存
        deleteHomeRedis();
        return BaseResult.success("更新板块成功");
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult deletePanelById(int id) {
        int result = tbPanelMapper.deleteByPrimaryKey(id);

        // 删除失败
        if (result != 1) {
            return BaseResult.fail("删除板块失败");
        }

        // 同步缓存
        deleteHomeRedis();
        return BaseResult.success("删除板块成功");
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult addPanel(TbPanel tbPanel) {

        // 判断是否为轮播图板块，首页只能有一个轮播图板块
        if (tbPanel.getType() == 0) {
            TbPanel isExistTbPanel = tbPanelMapper.getPanelByType(0);
            if (isExistTbPanel != null) {
                return BaseResult.fail("已有轮播图板块，轮播图板块仅能添加 1 个");
            }
        }

        tbPanel.setCreated(new Date());
        tbPanel.setUpdated(new Date());

        int result = tbPanelMapper.insert(tbPanel);

        // 添加板块失败
        if (result != 1) {
            return BaseResult.fail("添加板块失败！");
        }

        // 同步缓存
        deleteHomeRedis();
        return BaseResult.success("添加板块成功！");
    }

    /**
     * 删除首页缓存
     */
    private void deleteHomeRedis() {
        redisCacheManager.del(PRODUCT_HOME);
    }
}
