package com.yuu.ymall.web.admin.web.controller;

import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbExpress;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.service.ExpressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ExpressController
 * @Date 2019/6/15 18:58
 * @Created by Yuu
 */
@RestController
@Api(description = "快递管理")
@RequestMapping("express")
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    /**
     * 获取快递列表
     *
     * @param request 请求
     * @param search 搜索参数
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "获得所有快递")
    public DataTablesResult<TbExpress> getEXPressList(HttpServletRequest request, @RequestParam(value = "search[value]", required = false) String search) {
        DataTablesResult<TbExpress> dataTablesResult = expressService.getExpressList(request, search);
        return dataTablesResult;
    }

    /**
     * 保存快递信息
     *
     * @param tbExpress 快递信息
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "保存快递")
    public BaseResult save(TbExpress tbExpress) {
        BaseResult baseResult = expressService.save(tbExpress);
        return baseResult;
    }

    /**
     * 删除快递信息
     *
     * @param ids 快递的 id 集合
     * @return
     */
    @DeleteMapping("delete/{ids}")
    @ApiOperation(value = "删除快递")
    public BaseResult delete(@PathVariable Integer[] ids) {
        BaseResult baseResult = expressService.delete(ids);
        return baseResult;
    }


}
