package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.web.admin.mapper.TbOrderMapper;
import com.yuu.ymall.web.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname OrderServiceImpl
 * @Date 2019/5/15 22:35
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public int getAllOrderCount() {
        int orderCount = tbOrderMapper.getAllOrderCount();
        return tbOrderMapper.selectAll().size();
    }
}
