package com.yuu.ymall.web.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.mapper.TbItemMapper;
import com.yuu.ymall.web.admin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public DataTablesResult<TbItem> getItemListByCid(HttpServletRequest request, Long cid, String search) {
        DataTablesResult<TbItem> result = new DataTablesResult<>(request);

        Map<String, Object> params = new HashMap<>();
        params.put("cid", cid);
        params.put("search", search);

        // 分页查询
        PageHelper.startPage(result.getPageNum(), result.getLength());
        List<TbItem> tbItemList = tbItemMapper.getItemByCid(params);

        // 封装 PageInfo
        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItemList);
        result.setRecordsFiltered((int) tbItemPageInfo.getTotal());
        result.setRecordsTotal(tbItemMapper.getTbItemByCidCount(params));
        result.setDraw(result.getDraw());
        result.setData(tbItemList);

        return result;
    }
}
