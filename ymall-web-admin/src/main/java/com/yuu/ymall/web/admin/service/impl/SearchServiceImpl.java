package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.web.admin.commons.es.ESItem;
import com.yuu.ymall.web.admin.mapper.TbItemMapper;
import com.yuu.ymall.web.admin.mapper.TbOrderItemMapper;
import com.yuu.ymall.web.admin.repositories.ItemRepository;
import com.yuu.ymall.web.admin.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Yuu
 * @classname SearchSeviceImpl
 * @date 2019/7/9 19:50
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;


    @Override
    public void refreshItem(int type, Long itemId) {
        // 更新索引
        if (type == 0) {
            // 从数据库中查出数据
            TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
            // 设置 ESItem
            if (tbItem != null) {
                ESItem esItem = new ESItem();
                esItem.setId(tbItem.getId());
                esItem.setProductId(tbItem.getId());
                esItem.setProductName(tbItem.getTitle());
                esItem.setSubTitle(tbItem.getSellPoint());
                esItem.setSalePrice(tbItem.getPrice().doubleValue());
                esItem.setPicUrl(tbItem.getImages()[0]);
                esItem.setCid(tbItem.getCid());
                if (tbItem.getLimitNum() > tbItem.getNum()) {
                    esItem.setLimit(tbItem.getNum());
                } else {
                    esItem.setLimit(tbItem.getLimitNum());
                }
                // 查询该商品订单数量
                int orderNum = tbOrderItemMapper.selectOrderNumByItemId(tbItem.getId());
                esItem.setOrderNum(orderNum);
                // 更新索引
                itemRepository.save(esItem);
            }
        }

        // 删除索引
        else {
            itemRepository.deleteById(itemId);
        }
    }

    @Override
    public void importAllItems() {
        // 从数据库中查询所有商品
        List<TbItem> tbItemList = tbItemMapper.selectAll();

        List<ESItem> esItems = new ArrayList<>();
        for (TbItem tbItem : tbItemList) {
            ESItem esItem = new ESItem();
            esItem.setId(tbItem.getId());
            esItem.setProductId(tbItem.getId());
            esItem.setProductName(tbItem.getTitle());
            esItem.setSubTitle(tbItem.getSellPoint());
            esItem.setSalePrice(tbItem.getPrice().doubleValue());
            esItem.setPicUrl(tbItem.getImages()[0]);
            esItem.setCid(tbItem.getCid());
            if (tbItem.getLimitNum() > tbItem.getNum()) {
                esItem.setLimit(tbItem.getNum());
            } else {
                esItem.setLimit(tbItem.getLimitNum());
            }
            esItems.add(esItem);
        }

        // 批量新增
        itemRepository.saveAll(esItems);
    }
}
