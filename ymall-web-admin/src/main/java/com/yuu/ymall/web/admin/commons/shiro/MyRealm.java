package com.yuu.ymall.web.admin.commons.shiro;

import com.yuu.ymall.domain.TbUser;
import com.yuu.ymall.web.admin.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname MyRealm
 * @Date 2019/5/11 18:47
 * @Created by Yuu
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 先执行登录验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名密码
        String username = authenticationToken.getPrincipal().toString();
        TbUser tbUser = userService.getUserByUsername(username);
        if (tbUser != null) {
            // 得到用户账号和密码存放到 authenticationInfo 用于 Controller 层的权限判断，第三个参数随意不能为空
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(tbUser.getUsername(), tbUser.getPassword(),
                    tbUser.getUsername());
            return authenticationInfo;
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
