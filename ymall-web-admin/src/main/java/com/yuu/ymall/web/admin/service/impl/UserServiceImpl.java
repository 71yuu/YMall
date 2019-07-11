package com.yuu.ymall.web.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbUser;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.mapper.TbUserMapper;
import com.yuu.ymall.web.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
    public TbUser getUserByUsername(String username) {
        return tbUserMapper.getUserByUsername(username);
    }

    @Override
    public DataTablesResult<TbUser> getUserList(HttpServletRequest request, String search) {
        DataTablesResult<TbUser> result = new DataTablesResult<>(request);

        // 分页查询
        PageHelper.startPage(result.getPageNum(), result.getLength());
        List<TbUser> tbUserList = tbUserMapper.getUserList(search);

        // 封装 PageInfo
        PageInfo<TbUser> tbItemPageInfo = new PageInfo<>(tbUserList);
        result.setRecordsFiltered((int) tbItemPageInfo.getTotal());
        result.setRecordsTotal(tbUserMapper.getUserListCount(search));
        result.setDraw(result.getDraw());
        result.setData(tbUserList);

        return result;
    }

    @Override
    public Boolean validateUsername(Long id, String username) {
        // 根据用户名查询出会员
        TbUser tbUser = tbUserMapper.getUserByUsername(username);

        // 判断用户名是否为用户原用户名
        if (id != 0 && tbUser != null) {
            TbUser idTbUser = tbUserMapper.selectByPrimaryKey(id);
            if (idTbUser.getUsername().equals(tbUser.getUsername())) {
                return true;
            }
        }

        // 用户名不存在
        if (tbUser == null) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean validatePhone(Long id, String phone) {
        // 根据用户手机号查询出会员
        TbUser tbUser = tbUserMapper.getUserByPhone(phone);

        /*// 判断用户名是否为用户原用户名
        if (id != 0 && tbUser != null) {
            TbUser idTbUser = tbUserMapper.selectByPrimaryKey(id);
            if (idTbUser.getPhone().equals(tbUser.getPhone())) {
                return true;
            }
        }*/

        // 用户名不存在
        if (tbUser == null) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean validateEmail(Long id, String email) {
        // 根据用户手机号查询出会员
        TbUser tbUser = tbUserMapper.getUserByEmail(email);

        // 判断用户名是否为用户原用户名
        if (id != 0 && tbUser != null) {
            /*TbUser idTbUser = tbUserMapper.selectByPrimaryKey(id);
            if (idTbUser.getEmail().equals(tbUser.getEmail())) {
                return true;
            }*/
        }

        // 用户名不存在
        if (tbUser == null) {
            return true;
        }

        return false;
    }

    @Transactional
    @Override
    public BaseResult save(TbUser tbUser) {
        // 查询用户名是否被注册
        if (!validateUsername(tbUser.getId(), tbUser.getUsername())) {
            return BaseResult.fail("用户名已被注册!");
        }

        // 封装 TbUser
        TbUser newTbUser = new TbUser();
        BeanUtils.copyProperties(tbUser, newTbUser);
        newTbUser.setUpdated(new Date());
        newTbUser.setCreated(new Date());
        // 加密密码
        newTbUser.setPassword(DigestUtils.md5DigestAsHex(newTbUser.getPassword().getBytes()));

        // 新增会员
        tbUserMapper.insert(newTbUser);
        return BaseResult.success("保存用户成功！");
    }

    @Transactional
    @Override
    public BaseResult changePass(Long id, String password) {

        // 查询出旧的用户
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);

        // 用户不存在
        if (tbUser == null) {
            return BaseResult.fail("用户不存在！");
        }

        // 设置新密码
        tbUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        // 更新用户
        tbUserMapper.updateByPrimaryKey(tbUser);

        return BaseResult.success("更改密码成功！");
    }

    @Transactional
    @Override
    public BaseResult delete(Long[] ids) {
        for (Long id : ids) {
            if (id.equals(1l)) {
                return BaseResult.success("不能删除超级管理员！！！");
            }
            tbUserMapper.deleteByPrimaryKey(id);
        }
        return BaseResult.success("删除用户成功！");
    }

}
