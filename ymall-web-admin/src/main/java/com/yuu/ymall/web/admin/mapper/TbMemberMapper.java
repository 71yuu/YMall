package com.yuu.ymall.web.admin.mapper;

import com.yuu.ymall.commons.persistence.BaseMapper;
import com.yuu.ymall.domain.TbMember;

public interface TbMemberMapper extends BaseMapper<TbMember> {

    /**
     * 根据主键 id 删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 获取会员总数
     *
     * @return
     */
    int getAllMemberCount();
}