package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbOrderItem;
import com.yuu.ymall.web.admin.mapper.TbOrderItemMapper;
import com.yuu.ymall.web.admin.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname SystemServiceImpl
 * @Date 2019/5/11 20:21
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class SystemServiceImpl implements SystemService {

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Override
    public BaseResult getWeekHot() {
        List<TbOrderItem> tbOrderItemList = tbOrderItemMapper.getWeekHot();
        if (tbOrderItemList.size() == 0) {
            TbOrderItem tbOrderItem = new TbOrderItem();
            tbOrderItem.setTotal(0);
            tbOrderItem.setTitle("暂无数据");
            tbOrderItem.setPicPath("");
            return BaseResult.success(tbOrderItem);
        }
        return BaseResult.success(tbOrderItemList.get(0));
    }

}
