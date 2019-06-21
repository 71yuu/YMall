package com.yuu.ymall.web.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbExpress;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.mapper.TbExpressMapper;
import com.yuu.ymall.web.admin.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ExpressServiceImpl
 * @Date 2019/6/15 19:00
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private TbExpressMapper tbExpressMapper;

    @Override
    public DataTablesResult<TbExpress> getExpressList(HttpServletRequest request, String search) {
        DataTablesResult<TbExpress> result = new DataTablesResult<>(request);

        Map<String, Object> params = new HashMap<>();
        params.put("search", search);

        // 分页查询
        PageHelper.startPage(result.getPageNum(), result.getLength());
        List<TbExpress> tbExpressList = tbExpressMapper.getExpressList(params);

        // 封装 PageInfo
        PageInfo<TbExpress> tbItemPageInfo = new PageInfo<>(tbExpressList);
        result.setRecordsFiltered((int) tbItemPageInfo.getTotal());
        result.setRecordsTotal(tbExpressMapper.getTbExpressCount(params));
        result.setDraw(result.getDraw());
        result.setData(tbExpressList);

        return result;

    }

    @Transactional
    @Override
    public BaseResult save(TbExpress tbExpress) {

        // 设置更新时间
        tbExpress.setUpdated(new Date());

        // 新增
        if (tbExpress.getId() == null) {
            tbExpress.setCreated(new Date());
            tbExpressMapper.insert(tbExpress);
        }

        // 编辑
        else {
            tbExpressMapper.updateByPrimaryKey(tbExpress);
        }
        return BaseResult.success("保存物流信息成功！");
    }

    @Transactional
    @Override
    public BaseResult delete(Integer[] ids) {
        for (Integer id : ids) {
            tbExpressMapper.deleteByPrimaryKey(id);
        }
        return BaseResult.success("删除快递成功！");
    }
}
