package com.yuu.ymall.web.api.mapper;

import com.yuu.ymall.domain.TbAddress;

import java.util.List;

public interface TbAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbAddress record);

    TbAddress selectByPrimaryKey(Long id);

    List<TbAddress> selectAll();

    int updateByPrimaryKey(TbAddress record);

    /**
     * 获取会员地址列表
     *
     * @param userId 会员 id
     * @return
     */
    List<TbAddress> selectByUserId(Long userId);

    /**
     * 会员修改地址
     *
     * @param tbAddress 会员地址
     */
    int updateAddress(TbAddress tbAddress);

    /**
     * 移除默认地址
     *
     * @param userId 用户地址
     */
    int removeDefault(Long userId);
}
