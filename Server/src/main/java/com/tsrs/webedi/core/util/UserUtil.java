package com.tsrs.webedi.core.util;

import com.tsrs.webedi.common.persistence.model.User;
import com.tsrs.webedi.core.jwt.JWTToken;
import com.tsrs.webedi.core.shiro.ShiroKit;
import com.tsrs.webedi.core.shiro.ShiroUser;
import com.tsrs.webedi.core.shiro.factory.IShiro;
import com.tsrs.webedi.core.shiro.factory.ShiroFactroy;
import com.tsrs.webedi.modular.system.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

public class UserUtil {
    @Resource
    private IUserService userService;

    public ShiroUser getUserByToken(String token) {
        String account = JWTUtils.getAccount(token);
        IShiro shiroFactory = ShiroFactroy.me();
        User user = shiroFactory.user(account);
        return shiroFactory.shiroUser(user);
    }

    public static void login(User user) {
        IShiro shiroFactory = ShiroFactroy.me();
        User dbUser = shiroFactory.user(user.getAccount());
        String pwdMd5 = ShiroKit.md5(user.getPassword(), dbUser.getSalt());
        if (!dbUser.getPassword().equals(pwdMd5)) {
            throw new AuthenticationException("密码错误");
        }
        String jwt = JWTUtils.getToken(user);
        JWTToken token = new JWTToken(jwt);
        Subject currentUser = ShiroKit.getSubject();
        currentUser.login(token);
    }
}
