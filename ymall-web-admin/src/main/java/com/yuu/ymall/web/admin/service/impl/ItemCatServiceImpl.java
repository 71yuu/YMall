package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.domain.TbItemCat;
import com.yuu.ymall.web.admin.commons.dto.ZTreeNode;
import com.yuu.ymall.web.admin.commons.utils.DtoUtil;
import com.yuu.ymall.web.admin.mapper.TbItemCatMapper;
import com.yuu.ymall.web.admin.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname ItemCatServiceImpl
 * @Date 2019/6/3 14:53
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Value("${HEADER_CATE}")
    private String HEADER_CATE;

    @Override
    public List<ZTreeNode> getItemCatList(Long parentId, int type) {

        // 根据父 id 查询分类列表
        List<TbItemCat> tbItemCats = tbItemCatMapper.getItemCatList(parentId);

        // 转换成 ZTreeNode
        List<ZTreeNode> resultList = new ArrayList<>();

        // 如果 type = -1 不展示所有商品，type = 0 展示所有商品
        if (type == -1 && parentId == 0) {
            tbItemCats.remove(0);
        }
        for (TbItemCat tbItemCat : tbItemCats) {
            ZTreeNode node = DtoUtil.TbItemCat2ZTreeNode(tbItemCat);
            resultList.add(node);
        }

        return resultList;
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult saveItemCat(TbItemCat tbItemCat) {
        tbItemCat.setUpdated(new Date());

        int result;
        // 编辑商品分类
        if (tbItemCat.getId() != null) {
            result = tbItemCatMapper.updateByPrimaryKey(tbItemCat);
        }

        // 添加商品分类
        else {
            // 查询该子分类最大的排序值
            int maxSortOrder = tbItemCatMapper.getMaxSortOrder(tbItemCat.getParentId());
            // 默认排序值 +1
            tbItemCat.setSortOrder(maxSortOrder + 1);
            // 设置默认状态，开启
            tbItemCat.setStatus(1);
            tbItemCat.setCreated(new Date());
            result = tbItemCatMapper.insert(tbItemCat);
        }

        // 删除 Redis 缓存
        redisCacheManager.del(HEADER_CATE);

        if (result != 1) {
            return BaseResult.fail("保存商品分类失败");
        }

        return BaseResult.success("保存商品分类成功");
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult deleteItemCat(Long id) {

        // 查询该节点所有子节点
        List<ZTreeNode> nodes = getItemCatList(id, -1);
        if (nodes.size() > 0) {
            // 如果有子节点，则遍历删除子节点
            for (ZTreeNode node : nodes) {
                deleteItemCat((long) node.getId());
            }
        }

        // 没有则直接删除节点
        int result = tbItemCatMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            return BaseResult.fail("删除商品分类失败！");
        }

        // 删除 Redis 缓存
        redisCacheManager.del(HEADER_CATE);

        return BaseResult.success("删除商品分类成功");
    }
}
