package com.tsrs.webedi.core.shiro.factory;

import com.tsrs.webedi.core.shiro.ShiroUser;
import com.tsrs.webedi.common.persistence.model.User;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.util.List;

/**
 * 定义shirorealm所需数据的接口
 *
 * @author Tsrs-WebEDI
 * @date 2016年12月5日 上午10:23:34
 */
public interface IShiro {

    /**
     * 根据アカウント获取登录用户
     *
     * @param account アカウント
     */
    User user(String account);

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user 系统用户
     */
    ShiroUser shiroUser(User user);

    /**
     * 根据役割id获取役割名称
     *
     * @param roleId 役割id
     */
    String findRoleNameByRoleId(Integer roleId);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName);

}
