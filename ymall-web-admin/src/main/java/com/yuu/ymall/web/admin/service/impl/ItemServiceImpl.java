package com.yuu.ymall.web.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.domain.TbItem;
import com.yuu.ymall.domain.TbItemCat;
import com.yuu.ymall.domain.TbItemDesc;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.commons.dto.ItemDto;
import com.yuu.ymall.web.admin.commons.utils.IDUtil;
import com.yuu.ymall.web.admin.mapper.*;
import com.yuu.ymall.web.admin.service.ItemService;
import com.yuu.ymall.web.admin.service.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbPanelContentMapper tbPanelContentMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private SearchService searchService;

    @Value("${PRODUCT_ITEM}")
    private String PRODUCT_ITEM;

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

    @Transactional(readOnly = false)
    @Override
    public BaseResult deleteItem(Long[] ids) {
        // 删除商品
        for (Long id : ids) {
            tbItemMapper.deleteByPrimaryKey(id);
        }
        return BaseResult.success("删除商品成功！");
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult stopItem(Long id) {
        tbItemMapper.stopItemById(id);
        // 删除 ES 索引
        searchService.refreshItem(-1, id);
        return BaseResult.success("下架商品成功！");
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult startItem(Long id) {
        tbItemMapper.startItemById(id);
        // 增加索引
        searchService.refreshItem(0, id);
        return BaseResult.success("发布商品成功！");
    }

    @Transactional(readOnly = false)
    @Override
    public BaseResult saveItem(ItemDto itemDto) {
        // 封装 TbItem
        TbItem newTbItem = new TbItem();
        BeanUtils.copyProperties(itemDto, newTbItem);
        newTbItem.setUpdated(new Date());

        // 封装 TbItemDesc
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(itemDto.getDetail());
        tbItemDesc.setUpdated(new Date());

        // 编辑商品
        if (itemDto.getId() != null) {
            // 编辑商品
            TbItem oldTbItem = tbItemMapper.selectByPrimaryKey(itemDto.getId());
            newTbItem.setStatus(oldTbItem.getStatus());
            newTbItem.setCreated(oldTbItem.getCreated());
            tbItemMapper.updateByPrimaryKey(newTbItem);

            // 编辑商品详情
            tbItemDesc.setItemId(newTbItem.getId());
            tbItemDesc.setCreated(oldTbItem.getCreated());
            tbItemDescMapper.updateByPrimaryKey(tbItemDesc);

            // 同步缓存
            redisCacheManager.del(PRODUCT_ITEM + ":" + newTbItem.getId());

            // 更新 ES 索引库
            searchService.refreshItem(0, itemDto.getId());
        }

        // 新增商品
        else {
            // 新增商品
            Long id = IDUtil.getRandomId();
            newTbItem.setId(id);
            newTbItem.setStatus(1);
            newTbItem.setCreated(new Date());
            tbItemMapper.insert(newTbItem);

            // 新增商品详情
            tbItemDesc.setItemId(id);
            tbItemDesc.setCreated(new Date());
            tbItemDescMapper.insert(tbItemDesc);

            // 更新 ES 索引库
            searchService.refreshItem(0, id);
        }


        return BaseResult.success("保存商品成功！");
    }

    @Override
    public BaseResult getItemById(Long itemId) {
        ItemDto itemDto = new ItemDto();

        // 封装 TbItem 属性
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        BeanUtils.copyProperties(tbItem, itemDto);

        // 封装 Cname
        TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(tbItem.getCid());
        itemDto.setCname(tbItemCat.getName());

        // 封装 TbItemDesc
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        itemDto.setDetail(tbItemDesc.getItemDesc());

        return BaseResult.success(itemDto);
    }
}
