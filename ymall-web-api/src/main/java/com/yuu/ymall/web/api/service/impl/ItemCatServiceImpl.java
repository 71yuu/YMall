package com.yuu.ymall.web.api.service.impl;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.commons.utils.MapperUtil;
import com.yuu.ymall.domain.TbItemCat;
import com.yuu.ymall.web.api.dto.TbCate;
import com.yuu.ymall.web.api.mapper.TbItemCatMapper;
import com.yuu.ymall.web.api.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Yuu
 * @classname ItemCatServiceImpl
 * @date 2019/7/2 10:12
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
    public BaseResult getCateList() {
        List<TbCate> tbCateList = new ArrayList<>();
        // 查询缓存
        try {
            // 有缓存则读取
            String json = (String) redisCacheManager.get(HEADER_CATE);
            if (json != null) {
                tbCateList = MapperUtil.json2list(json, TbCate.class);
                return BaseResult.success(tbCateList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 从数据库中查询
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectAll();
        // 删除根分类，所有商品
        tbItemCats.remove(0);
        List<TbCate> tbCates = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {
            // 如果是分级分类，进行遍历设值
            if (tbItemCat.getParentId() == 0) {
                TbCate tbCate = new TbCate();
                tbCate.setId(tbItemCat.getId());
                tbCate.setName(tbItemCat.getName());
                // 查询父分类的所有子分类
                List<TbItemCat> tbItemCatList = tbItemCatMapper.selectTbCatByPid(tbItemCat.getId());
                List<TbCate> tbCateSons = new ArrayList<>();
                for (TbItemCat itemCat : tbItemCatList) {
                    TbCate tbCateSon = new TbCate();
                    tbCateSon.setId(itemCat.getId());
                    tbCateSon.setName(itemCat.getName());
                    tbCateSon.setCatesons(null);
                    tbCateSons.add(tbCateSon);
                }
                tbCate.setCatesons(tbCateSons);
                tbCates.add(tbCate);
            }
        }

        // 把结果添加至缓存
        try {
            String tbCateListJson = MapperUtil.obj2json(tbCates);
            redisCacheManager.set(HEADER_CATE, tbCateListJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return BaseResult.success(tbCates);
    }
}
