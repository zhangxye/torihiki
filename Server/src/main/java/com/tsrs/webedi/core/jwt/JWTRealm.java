package com.tsrs.webedi.core.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.tsrs.webedi.common.persistence.model.User;
import com.tsrs.webedi.core.shiro.ShiroUser;
import com.tsrs.webedi.core.shiro.factory.IShiro;
import com.tsrs.webedi.core.shiro.factory.ShiroFactroy;
import com.tsrs.webedi.core.util.JWTUtils;
import com.tsrs.webedi.modular.system.dao.UserMgrDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JWTRealm extends AuthorizingRealm {

    @Resource
    private UserMgrDao userService;

    // 让shiro支持我们自定义的token，即如果传入的token时JWTToken则放行
    // 必须重写不然shiro会报错
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    // 检验权限时调用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        DecodedJWT verify = JWTUtils.verify(principalCollection.toString());
        String account = verify.getClaim("account").asString();
        // 根据email查询用户的身份和权限
        User user = userService.getByAccount(account);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(user.getUserType());
//        info.addStringPermissions(user.getPermission());
        return info;
    }

    // 认证和鉴权时调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();// 重写了该类，实际上返回的是token
        String account = null;
        try {
            // 根据token获得登录用户的email
            account = JWTUtils.verify(token).getClaim("account").asString();
        } catch (Exception e) {
            throw new AuthenticationException("该token非法，可能被篡改或过期");
        }
        if (userService.getByAccount(account) == null) {
            throw new AuthenticationException("用户不存在");
        }

        IShiro shiroFactory = ShiroFactroy.me();
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = shiroFactory.user(account);
//        String pwdMd5 =  ShiroKit.md5(user.getPassword(), user.getSalt());
//        if(!user.getPassword().equals(pwdMd5)){
//            throw new AuthenticationException("密码错误");
//        }
        ShiroUser shiroUser = shiroFactory.shiroUser(user);
        shiroUser.setToken(token);
//        return shiroFactory.info(shiroUser, user, super.getName());
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }
}

