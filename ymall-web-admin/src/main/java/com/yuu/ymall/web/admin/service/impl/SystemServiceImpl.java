package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.commons.execption.YMallException;
import com.yuu.ymall.domain.TbBase;
import com.yuu.ymall.domain.TbLog;
import com.yuu.ymall.domain.TbOrderItem;
import com.yuu.ymall.domain.TbShiroFilter;
import com.yuu.ymall.web.admin.mapper.TbBaseMapper;
import com.yuu.ymall.web.admin.mapper.TbLogMapper;
import com.yuu.ymall.web.admin.mapper.TbOrderItemMapper;
import com.yuu.ymall.web.admin.mapper.TbShiroFilterMapper;
import com.yuu.ymall.web.admin.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger log = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Autowired
    private TbShiroFilterMapper tbShiroFilterMapper;

    @Autowired
    private TbBaseMapper tbBaseMapper;

    @Autowired
    private TbLogMapper tbLogMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Override
    public List<TbShiroFilter> getShiroFilter() {
        return tbShiroFilterMapper.getShiroFilter();
    }

    @Override
    public TbBase getBase() {
        TbBase tbBase =  tbBaseMapper.getBase();
        if (tbBase == null) {
            throw new YMallException("获取系统配置失败");
        }
        return tbBase;
    }

    @Transactional(readOnly = false)
    @Override
    public int addLog(TbLog tbLog) {
        if (tbLogMapper.insert(tbLog) != 1) {
            throw new YMallException("保存日志失败");
        }
        return 1;
    }

    @Override
    public TbOrderItem getWeekHot() {
        List<TbOrderItem> tbOrderItemList = tbOrderItemMapper.getWeekHot();
        if (tbOrderItemList == null) {
            throw new YMallException("获取热销商品数据失败");
        }
        if (tbOrderItemList.size() == 0) {
            TbOrderItem tbOrderItem = new TbOrderItem();
            tbOrderItem.setTotal(0);
            tbOrderItem.setTitle("暂无数据");
            tbOrderItem.setPicPath("");
            return tbOrderItem;
        }
        return tbOrderItemList.get(0);
    }

}
