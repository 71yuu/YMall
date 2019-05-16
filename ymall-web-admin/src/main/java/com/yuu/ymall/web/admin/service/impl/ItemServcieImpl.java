package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.web.admin.mapper.TbItemMapper;
import com.yuu.ymall.web.admin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname ItemServcieImpl
 * @Date 2019/5/15 22:28
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class ItemServcieImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public int getAllItemCount() {
        int itemCount = tbItemMapper.getAllItemCount();
        return itemCount;
    }
}
