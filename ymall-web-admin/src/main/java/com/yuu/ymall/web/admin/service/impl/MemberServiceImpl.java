package com.yuu.ymall.web.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.domain.TbMember;
import com.yuu.ymall.web.admin.commons.consts.Consts;
import com.yuu.ymall.web.admin.commons.dto.DataTablesResult;
import com.yuu.ymall.web.admin.mapper.TbMemberMapper;
import com.yuu.ymall.web.admin.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    @Override
    public DataTablesResult<TbMember> getMemberList(HttpServletRequest request, String search) {
        // 初始化 DataTableResult
        DataTablesResult<TbMember> result = new DataTablesResult<>(request);

        // 设置 PageHelper
        PageHelper.startPage(result.getPageNum(), result.getLength());
        List<TbMember> tbMemberList = tbMemberMapper.getMemberList(search);

        // 删除密码
        for (TbMember tbMember : tbMemberList) {
            tbMember.setPassword("");
        }

        // 封装 PageHelper
        PageInfo<TbMember> tbMemberPageInfo = new PageInfo<>(tbMemberList);
        result.setRecordsFiltered((int) tbMemberPageInfo.getTotal());
        result.setRecordsTotal(tbMemberMapper.getMemberListCount(search));
        result.setDraw(result.getDraw());
        result.setData(tbMemberList);

        return result;
    }

    @Transactional
    @Override
    public BaseResult banMember(Long id) {
        // 查询会员
        TbMember tbMember = tbMemberMapper.selectByPrimaryKey(id);

        // 设置会员状态
        tbMember.setState(Consts.MEMBER_BAN);
        tbMember.setUpdated(new Date());

        // 更新会员
        tbMemberMapper.updateByPrimaryKey(tbMember);

        return BaseResult.success("封禁会员成功！");
    }

    @Transactional
    @Override
    public BaseResult startMember(Long[] ids) {
        for (Long id : ids) {
            // 查询会员
            TbMember tbMember = tbMemberMapper.selectByPrimaryKey(id);

            // 设置会员状态
            tbMember.setState(Consts.MEMBER_START);
            tbMember.setUpdated(new Date());

            // 更新会员
            tbMemberMapper.updateByPrimaryKey(tbMember);
        }
        return BaseResult.success("解封会员成功！");
    }

    @Transactional
    @Override
    public BaseResult saveMember(TbMember tbMember) {

        // 查询用户名、手机号、邮箱是否被注册
        if (!getMemberByUsername(tbMember.getUsername(), tbMember.getId())) {
            return BaseResult.fail("用户名已被注册!");
        } else if (!getMemberByPhone(tbMember.getPhone(), tbMember.getId())) {
            return BaseResult.fail("手机号已被注册！");
        } else if (!getMemberByEmail(tbMember.getEmail(), tbMember.getId())) {
            return BaseResult.fail("邮箱已被注册！");
        }

        // 封装 TbMember
        TbMember newTbMember = new TbMember();
        BeanUtils.copyProperties(tbMember, newTbMember);
        newTbMember.setUpdated(new Date());


        // 编辑会员
        if (tbMember.getId() != null && tbMember.getId() != 0) {
            // 查询出旧会员，封装数据
            TbMember oldTbMember = tbMemberMapper.selectByPrimaryKey(tbMember.getId());
            newTbMember.setState(oldTbMember.getState());
            newTbMember.setCreated(oldTbMember.getCreated());
            newTbMember.setPassword(oldTbMember.getPassword());

            // 更新会员
            tbMemberMapper.updateByPrimaryKey(newTbMember);
        }

        // 新增会员
        else {
            // 设置初始值
            newTbMember.setCreated(new Date());
            newTbMember.setState(Consts.MEMBER_START);

            // 加密密码
            newTbMember.setPassword(DigestUtils.md5DigestAsHex(tbMember.getPassword().getBytes()));

            // 新增会员
            tbMemberMapper.insert(newTbMember);
        }
        return BaseResult.success("保存会员成功！");
    }

    @Override
    public Boolean getMemberByUsername(String username, Long id) {
        TbMember tbMember = tbMemberMapper.getMemberByUsername(username);

        TbMember idTebMember = null;
        if (id != null || id != 0) {
            idTebMember = tbMemberMapper.selectByPrimaryKey(id);
            if (idTebMember != null && tbMember != null) {
                if (idTebMember.getUsername().equals(tbMember.getUsername())) {
                    return true;
                }
            }
        }

        // 会员不存在，返回 true
        if (tbMember == null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean getMemberByPhone(String phone, Long id) {
        TbMember tbMember = tbMemberMapper.getMemberByPhone(phone);

        TbMember idTebMember = null;
        if (id != null || id != 0) {
            idTebMember = tbMemberMapper.selectByPrimaryKey(id);
            if (idTebMember != null && tbMember != null) {
                if (idTebMember.getPhone().equals(tbMember.getPhone())) {
                    return true;
                }
            }
        }

        // 会员不存在，返回 true
        if (tbMember == null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean getMemberByEmail(String email, Long id) {
        TbMember tbMember = tbMemberMapper.getMemberByEmail(email);

        TbMember idTebMember = null;
        if (id != null || id != 0) {
            idTebMember = tbMemberMapper.selectByPrimaryKey(id);
            if (idTebMember != null && tbMember != null) {
                if (idTebMember.getEmail().equals(tbMember.getEmail())) {
                    return true;
                }
            }
        }

        // 会员不存在，返回 true
        if (tbMember == null) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public BaseResult changeMemberPassword(Long id, String password) {

        // 根据 id 查询会员
        TbMember tbMember = tbMemberMapper.selectByPrimaryKey(id);

        // 用户不存在
        if (tbMember == null) {
            return BaseResult.fail("用户不存在！");
        }

        // 设置新密码
        tbMember.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        // 更新会员
        tbMemberMapper.updateByPrimaryKey(tbMember);

        return BaseResult.success("更改密码成功！");
    }

    @Transactional
    @Override
    public BaseResult deleteMember(Long[] ids) {
        for (Long id : ids) {
            tbMemberMapper.deleteByPrimaryKey(id);
        }
        return BaseResult.success("删除会员成功！");
    }

    @Override
    public DataTablesResult<TbMember> getMemberBanList(HttpServletRequest request, String search) {
        // 初始化 DataTableResult
        DataTablesResult<TbMember> result = new DataTablesResult<>(request);

        // 设置 PageHelper
        PageHelper.startPage(result.getPageNum(), result.getLength());
        List<TbMember> tbMemberList = tbMemberMapper.getMemberBanList(search);

        // 删除密码
        for (TbMember tbMember : tbMemberList) {
            tbMember.setPassword("");
        }

        // 封装 PageHelper
        PageInfo<TbMember> tbMemberPageInfo = new PageInfo<>(tbMemberList);
        result.setRecordsFiltered((int) tbMemberPageInfo.getTotal());
        result.setRecordsTotal(tbMemberMapper.getMemberBanListCount(search));
        result.setDraw(result.getDraw());
        result.setData(tbMemberList);

        return result;
    }
}
