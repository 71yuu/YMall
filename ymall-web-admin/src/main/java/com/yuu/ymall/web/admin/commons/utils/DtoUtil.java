package com.yuu.ymall.web.admin.commons.utils;

import com.yuu.ymall.domain.TbItemCat;
import com.yuu.ymall.domain.TbPanel;
import com.yuu.ymall.web.admin.commons.dto.ZTreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Classname DtoUtil
 * @Date 2019/5/20 9:21
 * @Created by Yuu
 */
public class DtoUtil {

    private final static Logger log = LoggerFactory.getLogger(DtoUtil.class);

    /**
     * 板块类目封装 ZTreeNode 对象
     *
     * @param tbPanel 板块类目
     * @return
     */
    public static ZTreeNode TbPanel2ZTreeNode(TbPanel tbPanel) {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setId(tbPanel.getId());
        zTreeNode.setIsParent(false);
        zTreeNode.setPId(0);
        zTreeNode.setName(tbPanel.getName());
        zTreeNode.setSortOrder(tbPanel.getSortOrder());
        zTreeNode.setStatus(tbPanel.getStatus());
        zTreeNode.setLimitNum(tbPanel.getLimitNum());
        zTreeNode.setType(tbPanel.getType());
        return zTreeNode;
    }


    /**
     * 商品分类封装 ZTreeNode 对象
     *
     * @param tbItemCat 商品分类
     * @return
     */
    public static ZTreeNode TbItemCat2ZTreeNode(TbItemCat tbItemCat) {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setId(Math.toIntExact(tbItemCat.getId()));
        zTreeNode.setStatus(tbItemCat.getStatus());
        zTreeNode.setSortOrder(tbItemCat.getSortOrder());
        zTreeNode.setName(tbItemCat.getName());
        zTreeNode.setPId(Math.toIntExact(tbItemCat.getParentId()));
        zTreeNode.setIsParent(tbItemCat.getIsParent());
        return zTreeNode;
    }
}
