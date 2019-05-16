package com.yuu.ymall.web.admin.service.impl;

import com.yuu.ymall.web.admin.mapper.TbUserMapper;
import com.yuu.ymall.domain.TbUser;
import com.yuu.ymall.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @Classname UserServiceImpl
 * @Date 2019/5/11 18:49
 * @Created by Yuu
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public Set<String> getRoles(String username) {
        return tbUserMapper.getRoles(username);
    }

    @Override
    public Set<String> getPermissions(String username) {
        return tbUserMapper.getPermissions(username);
    }

    @Override
    public TbUser getUserByUsername(String username) {
        return tbUserMapper.getUserByUsername(username);
    }
}
