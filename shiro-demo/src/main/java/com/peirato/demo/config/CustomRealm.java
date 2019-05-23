package com.peirato.demo.config;

import com.peirato.demo.user.User;
import com.peirato.demo.user.UserServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * shiro登陆和权限认证
 * @author peirato.
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserServer userServer;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("--权限认证--");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //查询该用户角色
        String role = userServer.getRole(username);
        Set<String> set = new HashSet<>();
        set.add(role);
        simpleAuthorizationInfo.setRoles(set);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取身份验证信息
     * 用过Realm来回去应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的AuthenticationInfo实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("--身份认证--");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从数据库查询用户名和密码
        String password = new String((char[]) token.getCredentials());
        User user = userServer.auth(token.getUsername(), password);

        if (user == null) {
            throw new AccountException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }
}
