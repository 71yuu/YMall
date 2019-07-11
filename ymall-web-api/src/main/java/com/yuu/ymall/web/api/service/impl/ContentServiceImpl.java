package com.yuu.ymall.web.api.service.impl;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.commons.utils.MapperUtil;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.domain.TbPanel;
import com.yuu.ymall.web.api.dto.TbPanelContentDto;
import com.yuu.ymall.web.api.dto.TbPanelDto;
import com.yuu.ymall.web.api.mapper.TbItemMapper;
import com.yuu.ymall.web.api.mapper.TbPanelContentMapper;
import com.yuu.ymall.web.api.mapper.TbPanelMapper;
import com.yuu.ymall.web.api.service.ContentService;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 推荐板块 key
     */
    @Value("${RECOMEED_PANEL}")
    private String RECOMEED_PANEL;

    /**
     * 推荐板块数据库 id
     */
    @Value("${RECOMEED_PANEL_ID}")
    private Integer RECOMEED_PANEL_ID;

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
                    Integer limitNum = tbItem.getLimitNum();
                    Integer num = tbItem.getNum();
                    if (limitNum > num) {
                        limitNum = num;
                    }
                    tbPanelContentDto.setLimit(limitNum);
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

    @Override
    public BaseResult getRecommendGoods() {
        TbPanelDto tbPanelDto = new TbPanelDto();

        // 有缓存，从缓存中共读取
        String redisJson = (String) redisCacheManager.get(RECOMEED_PANEL);
        if (StringUtils.isNoneBlank(redisJson)) {
            try {
                tbPanelDto = MapperUtil.json2pojo(redisJson, TbPanelDto.class);
                return BaseResult.success(tbPanelDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 没有缓存，从数据库中读取
        TbPanel tbPanel = tbPanelMapper.selectByPrimaryKey(RECOMEED_PANEL_ID);
        if (tbPanel != null) {
            tbPanelDto.setId(tbPanel.getId());
            tbPanelDto.setName(tbPanel.getName());
            tbPanelDto.setType(tbPanel.getType());
            // 查询板块内容
            List<TbPanelContentDto> tbPanelContentDtos = tbPanelContentMapper.selectContentByPid(tbPanel.getId());
            // 获取商品相关信息
            for (TbPanelContentDto tbPanelContentDto : tbPanelContentDtos) {
                TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbPanelContentDto.getProductId());
                if (tbItem != null) {
                    tbPanelContentDto.setProductName(tbItem.getTitle());
                    tbPanelContentDto.setSubTitle(tbItem.getSellPoint());
                    tbPanelContentDto.setSalePrice(tbItem.getPrice());
                }
            }

            tbPanelDto.setPanelContentDtos(tbPanelContentDtos);
        }

        if (tbPanelDto != null) {
            // 将结果添加至缓存
            try {
                String tbPanelDtoJson = MapperUtil.obj2json(tbPanelDto);
                redisCacheManager.set(RECOMEED_PANEL, tbPanelDtoJson);
                return BaseResult.success(tbPanelDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return BaseResult.fail("获取推荐板块失败");
    }
}
