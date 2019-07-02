package com.yuu.ymall.web.api.service.impl;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.commons.utils.MapperUtil;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.web.api.dto.TbPanelContentDto;
import com.yuu.ymall.web.api.dto.TbPanelDto;
import com.yuu.ymall.web.api.mapper.TbItemMapper;
import com.yuu.ymall.web.api.mapper.TbPanelContentMapper;
import com.yuu.ymall.web.api.mapper.TbPanelMapper;
import com.yuu.ymall.web.api.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Yuu
 * @classname ContentServiceImpl
 * @date 2019/7/2 13:51
 */
@Service
@Transactional(readOnly = true)
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbPanelContentMapper tbPanelContentMapper;

    @Autowired
    private TbPanelMapper tbPanelMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    /**
     * 首页板块内容的 key
     */
    @Value("${PRODUCT_HOME}")
    private String PRODUCT_HOME;

    @Override
    public BaseResult getHome() {

        List<TbPanelDto> tbPanelDtos = new ArrayList<>();

        // 查询缓存
        try {
            // 有缓存从缓存读取
            String tbPanelsJson = (String) redisCacheManager.get(PRODUCT_HOME);
            if (tbPanelsJson != null) {
                tbPanelDtos = MapperUtil.json2list(tbPanelsJson, TbPanelDto.class);
                return BaseResult.success(tbPanelDtos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 没有缓存从数据库中读取
        tbPanelDtos = tbPanelMapper.selectAll();
        for (TbPanelDto tbPanelDto : tbPanelDtos) {
            List<TbPanelContentDto> tbPanelContents = tbPanelContentMapper.selectContentByPid(tbPanelDto.getId());
            // 设置商品相关信息
            for (TbPanelContentDto tbPanelContentDto : tbPanelContents) {
                TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbPanelContentDto.getProductId());
                if (tbItem != null) {
                    tbPanelContentDto.setSalePrice(tbItem.getPrice());
                    tbPanelContentDto.setProductName(tbItem.getTitle());
                    tbPanelContentDto.setSubTitle(tbItem.getSellPoint());
                }
            }
            tbPanelDto.setPanelContentDtos(tbPanelContents);
        }

        // 将数据存入缓存
        try {
            String tbPanelsJson = MapperUtil.obj2json(tbPanelDtos);
            redisCacheManager.set(PRODUCT_HOME, tbPanelsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return BaseResult.success(tbPanelDtos);
    }
}
