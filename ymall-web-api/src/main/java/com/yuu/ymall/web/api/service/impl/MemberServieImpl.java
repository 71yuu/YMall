package com.yuu.ymall.web.api.service.impl;

import com.yuu.ymall.commons.consts.Consts;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.commons.utils.MapperUtil;
import com.yuu.ymall.commons.utils.SendSmsUtil;
import com.yuu.ymall.domain.TbAddress;
import com.yuu.ymall.domain.TbMember;
import com.yuu.ymall.web.api.common.utils.EmailUtil;
import com.yuu.ymall.web.api.common.utils.QiniuUtil;
import com.yuu.ymall.web.api.dto.EmailCode;
import com.yuu.ymall.web.api.dto.Member;
import com.yuu.ymall.web.api.dto.MemberLogin;
import com.yuu.ymall.web.api.mapper.TbAddressMapper;
import com.yuu.ymall.web.api.mapper.TbMemberMapper;
import com.yuu.ymall.web.api.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author by Yuu
 * @classname MemberServieImpl
 * @date 2019/6/24 9:05
 */
@Service
@Transactional(readOnly = true)
public class MemberServieImpl implements MemberService {

    @Autowired
    private TbMemberMapper tbMemberMapper;

    @Autowired
    private TbAddressMapper tbAddressMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public BaseResult checkPhone(String phone) {
        TbMember tbMember = tbMemberMapper.selectByPhone(phone);

        // 手机号已存在
        if (tbMember != null) {
            return BaseResult.success(true);
        }

        return BaseResult.success(false);
    }

    @Override
    public BaseResult sendSms(String phone) {
        // 调用短信发送工具发送短信,返回验证码
        String vercode = SendSmsUtil.sendSms(phone);
        return BaseResult.success((Object)vercode);
    }

    @Transactional
    @Override
    public BaseResult register(TbMember tbMember) {
        tbMember.setPassword(DigestUtils.md5DigestAsHex(tbMember.getPassword().getBytes()));
        tbMember.setCreated(new Date());
        tbMember.setUpdated(new Date());
        tbMember.setState(Consts.MEMBER_STATUS_NORMAL);
        tbMemberMapper.register(tbMember);
        return BaseResult.success();
    }

    @Override
    public BaseResult login(MemberLogin memberLogin, HttpSession session) {

        // 账号
        String account = memberLogin.getAccount();

        // 手机号正则表达式
        String phonePattern = "1[3|4|5|7|8][0-9]\\d{8}";

        // 邮箱正则表达式
        String emailPattern = "(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+";

        // 手机号登录
        if (Pattern.matches(phonePattern, account)) {
            TbMember tbMember = tbMemberMapper.selectByPhone(account);
            return verLogin(memberLogin, tbMember, session);
        }

        // 邮箱登录
        else if (Pattern.matches(emailPattern, account)) {
            TbMember tbMember = tbMemberMapper.selectByEmail(account);
            return verLogin(memberLogin, tbMember, session);
        }

        // 账号格式错误
        else {
            return BaseResult.fail("账号格式错误，只能为邮箱或手机号！");
        }
    }

    @Override
    public BaseResult getMemberByToken(String token) {
        // 从 Redis 从获取登录信息
        String json = (String) redisCacheManager.get("SESSION:" + token);
        TbMember tbMember = null;
        try {
            tbMember = MapperUtil.json2pojo(json, TbMember.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 没有登录信息，已过期
        if (tbMember == null) {
            return BaseResult.fail("用户登录已过期！");
        }

        // 没有过期，再次登录，重新设置过期时间
        redisCacheManager.expire("SESSION:" + token, SESSION_EXPIRE);
        return BaseResult.success(tbMember);
    }

    @Override
    public BaseResult logout(String token) {
        Boolean flag = redisCacheManager.del("SESSION:" + token);
        if (!flag) {
            return BaseResult.fail("退出登录失败！");
        }
        return BaseResult.success("退出登录成功");
    }

    @Override
    public BaseResult checkAccount(String account) {
        TbMember tbMember = tbMemberMapper.selectByPhoneOrEmail(account);
        // 不存在
        if (tbMember == null) {
            return BaseResult.success(false);
        }
        // 存在
        return BaseResult.success(true);
    }

    @Transactional
    @Override
    public BaseResult updatePassword(MemberLogin memberLogin) {

        // 账号
        String account = memberLogin.getAccount();

        // 密码
        String password = memberLogin.getPassword();

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return BaseResult.fail("账号密码不能为空");
        }
        tbMemberMapper.updatePassword(account, DigestUtils.md5DigestAsHex(password.getBytes()));
        return BaseResult.success("修改会员密码成功");
    }

    @Override
    public BaseResult sendVerCode(String account) {
        // 手机号正则表达式
        String phonePattern = "1[3|4|5|7|8][0-9]\\d{8}";

        // 邮箱正则表达式
        String emailPattern = "(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+";

        // 发送手机验证码
        if (Pattern.matches(phonePattern, account)) {
            // 调用短信发送工具发送短信,返回验证码
            String vercode = SendSmsUtil.sendSms(account);
            return BaseResult.success((Object)vercode);
        }

        // 发送邮箱验证码
        else if (Pattern.matches(emailPattern, account)) {
            String code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
            EmailCode emailCode = new EmailCode(account, code);
            emailUtil.sendTemplateEmail(account, "【YMALL商城】找回密码", "reset-pass", emailCode);
            return BaseResult.success((Object)code);
        }

        // 账号格式错误
        else {
            return BaseResult.fail("账号格式错误，只能为邮箱或手机号！");
        }
    }

    @Override
    public BaseResult getAddressList(Long userId) {
        List<TbAddress> tbAddressList = tbAddressMapper.selectByUserId(userId);
        // 拼接详细地址
        for (TbAddress tbAddress : tbAddressList) {
            String state = tbAddress.getState();
            String city = tbAddress.getCity();
            String district = tbAddress.getDistrict();
            String streetName = tbAddress.getStreetName();
            StringBuffer detailsAddress = new StringBuffer();
            detailsAddress.append(state).append(" ").append(city).append(" ").append(district)
                    .append(" ").append(streetName);
            tbAddress.setDetailsAddress(detailsAddress.toString());
        }
        return BaseResult.success(tbAddressList);
    }

    @Transactional
    @Override
    public BaseResult updateAddress(TbAddress tbAddress) {
        if (tbAddress.getIsDefault()) {
            tbAddressMapper.removeDefault(tbAddress.getUserId());
        }
        tbAddressMapper.updateAddress(tbAddress);
        return BaseResult.success("会员修改地址成功");
    }

    @Transactional
    @Override
    public BaseResult addAdderss(TbAddress tbAddress) {
        if (tbAddress.getIsDefault()) {
            tbAddressMapper.removeDefault(tbAddress.getUserId());
        }
        tbAddressMapper.insert(tbAddress);
        return BaseResult.success("会员增加地址");
    }

    @Transactional
    @Override
    public BaseResult delAddress(Long id) {
        tbAddressMapper.deleteByPrimaryKey(id);
        return BaseResult.success("会员删除地址成功");
    }

    @Transactional
    @Override
    public BaseResult uploadImg(Member member) {

        // 图片 base64
        String imgData = member.getImgData();

        // 会员 id
        Long userId = member.getUserId();

        // Redis token
        String token = member.getToken();

        // 七牛云上传图片
        String base64 = QiniuUtil.base64Data(imgData);
        String imgPath = QiniuUtil.qiniuBase64Upload(base64);

        // 获取会员
        TbMember tbMember = tbMemberMapper.selectByPrimaryKey(userId);
        if (tbMember == null) {
            return BaseResult.fail("获取用户信息失败");
        }

        // 更新数据库数据
        tbMember.setFile(imgPath);
        tbMemberMapper.updateByPrimaryKey(tbMember);

        // 更新 Redis 缓存
        // Redis key
        String redisKey = "SESSION:" + token;
        String memberJson = null;
        try {
            memberJson = MapperUtil.obj2json(tbMember);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisCacheManager.set(redisKey, memberJson);

        return BaseResult.success((Object) imgPath);
    }

    @Transactional
    @Override
    public BaseResult updateUsername(Member member) {

        // 会员 id
        Long userId = member.getUserId();

        // Redis key
        String token = member.getToken();

        // 用户名
        String username = member.getUsername();

        // 获取会员
        TbMember tbMember = tbMemberMapper.selectByPrimaryKey(userId);
        if (tbMember == null) {
            return BaseResult.fail("获取用户信息失败");
        }

        // 更新数据库数据
        tbMember.setUsername(username);
        tbMemberMapper.updateByPrimaryKey(tbMember);

        // 更新 Redis 缓存
        // Redis key
        String redisKey = "SESSION:" + token;
        String memberJson = null;
        try {
            memberJson = MapperUtil.obj2json(tbMember);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisCacheManager.set(redisKey, memberJson);

        return BaseResult.success(tbMember);
    }

    @Transactional
    @Override
    public BaseResult updatePhone(Member member) {

        // 会员 id
        Long userId = member.getUserId();

        // Redis key
        String token = member.getToken();

        // 手机号
        String phone = member.getPhone();

        // 获取会员
        TbMember tbMember = tbMemberMapper.selectByPrimaryKey(userId);
        if (tbMember == null) {
            return BaseResult.fail("获取用户信息失败");
        }

        // 更新数据库数据
        tbMember.setPhone(phone);
        tbMemberMapper.updateByPrimaryKey(tbMember);

        // 更新 Redis 缓存
        // Redis key
        String redisKey = "SESSION:" + token;
        String memberJson = null;
        try {
            memberJson = MapperUtil.obj2json(tbMember);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisCacheManager.set(redisKey, memberJson);

        return BaseResult.success(tbMember);
    }

    @Transactional
    @Override
    public BaseResult updatePass(Member member) {
        // 会员 id
        Long userId = member.getUserId();

        // Redis key
        String token = member.getToken();

        // 密码
        String password = member.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 获取会员
        TbMember tbMember = tbMemberMapper.selectByPrimaryKey(userId);
        if (tbMember == null) {
            return BaseResult.fail("获取用户信息失败");
        }

        // 更新数据库数据
        tbMember.setPassword(md5Password);
        tbMemberMapper.updateByPrimaryKey(tbMember);

        // 更新 Redis 缓存
        // Redis key
        String redisKey = "SESSION:" + token;
        String memberJson = null;
        try {
            memberJson = MapperUtil.obj2json(tbMember);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisCacheManager.set(redisKey, memberJson);

        return BaseResult.success(tbMember);
    }

    @Override
    public BaseResult sendEmailCode(String email) {
        String code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
        EmailCode emailCode = new EmailCode(email, code);
        emailUtil.sendTemplateEmail(email, "【YMALL商城】修改邮箱", "update-email", emailCode);
        return BaseResult.success((Object)code);
    }

    @Override
    public BaseResult checkEmail(String email) {
        TbMember tbMember = tbMemberMapper.selectByEmail(email);
        // 邮箱存在
        if (tbMember != null) {
            return BaseResult.success(true);
        }
        // 邮箱不存在
        return BaseResult.success(false);
    }

    @Transactional
    @Override
    public BaseResult updateEmail(Member member) {
        // 会员 id
        Long userId = member.getUserId();

        // Redis key
        String token = member.getToken();

        // 邮箱
        String email = member.getEmail();

        // 获取会员
        TbMember tbMember = tbMemberMapper.selectByPrimaryKey(userId);
        if (tbMember == null) {
            return BaseResult.fail("获取用户信息失败");
        }

        // 更新数据库数据
        tbMember.setEmail(email);
        tbMemberMapper.updateByPrimaryKey(tbMember);

        // 更新 Redis 缓存
        // Redis key
        String redisKey = "SESSION:" + token;
        String memberJson = null;
        try {
            memberJson = MapperUtil.obj2json(tbMember);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisCacheManager.set(redisKey, memberJson);

        return BaseResult.success(tbMember);
    }

    /**
     * 保存会员 session
     *
     * @param memberLogin 会员登录信息
     * @param tbMember 会员
     * @param session session
     * @return
     */
    private BaseResult verLogin(MemberLogin memberLogin, TbMember tbMember, HttpSession session) {

        // 获取 MD5 加密密码
        String md5Password = DigestUtils.md5DigestAsHex(memberLogin.getPassword().getBytes());

        if (tbMember != null && tbMember.getPassword().equals(md5Password)) {
            // 判断账户状态是否正常
            if (Consts.MEMBER_STATUS_BAN == (tbMember.getState())) {
                return BaseResult.fail("您的账号已被封禁，请联系管理员进行解封！");
            }

            // 自动登录，将登录信息保存在 Redis 中
            if (memberLogin.getAuto()) {
                try {
                    String token = UUID.randomUUID().toString();
                    tbMember.setToken(token);
                    // 将会员登录信息，保存到 Redis
                    redisCacheManager.set("SESSION:" + token, MapperUtil.obj2json(tbMember));
                    // 设置过期时间
                    redisCacheManager.expire("SESSION:" + token, SESSION_EXPIRE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 不自动登录，将登录信息保存在 HttpSession 中
            else {
                // 设置 token 为空
                tbMember.setToken("");
                session.setAttribute("memberLogin", tbMember);
            }

            return BaseResult.success(tbMember);
        }



        return BaseResult.fail("账号或密码错误！");
    }
}
