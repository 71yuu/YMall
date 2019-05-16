package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.web.admin.mapper.TbMemberMapper;
import com.yuu.ymall.web.admin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname MemberServiceImpl
 * @Date 2019/5/15 22:12
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private TbMemberMapper tbMemberMapper;

    @Override
    public int getAllMemberCount() {
        int memberCount = tbMemberMapper.getAllMemberCount();
        return memberCount;
    }
}
