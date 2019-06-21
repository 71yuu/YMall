package com.yuu.ymall.web.admin.service;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbExpress;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ExpressService
 * @Date 2019/6/15 18:59
 * @Created by Yuu
 */
public interface ExpressService {

    /**
     * 获取快递列表
     *
     * @param request 请求
     * @param search 搜索参数
     * @return
     */
    DataTablesResult<TbExpress> getExpressList(HttpServletRequest request, String search);

    /**
     * 保存快递
     *
     * @param tbExpress 快递
     * @return
     */
    BaseResult save(TbExpress tbExpress);

    /**
     * 删除快递
     * @param ids
     * @return
     */
    BaseResult delete(Integer[] ids);
}
