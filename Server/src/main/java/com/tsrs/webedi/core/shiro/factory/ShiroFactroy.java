package com.tsrs.webedi.core.shiro.factory;

import com.tsrs.webedi.common.constant.factory.ConstantFactory;
import com.tsrs.webedi.common.constant.state.ManagerStatus;
import com.tsrs.webedi.common.persistence.dao.RoleMapper;
import com.tsrs.webedi.common.persistence.model.RouteConfig;
import com.tsrs.webedi.common.persistence.model.User;
import com.tsrs.webedi.core.shiro.ShiroUser;
import com.tsrs.webedi.core.util.Convert;
import com.tsrs.webedi.core.util.SpringContextHolder;
import com.tsrs.webedi.modular.system.dao.UserMgrDao;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    @Resource
    private UserMgrDao userMgrDao;

    @Resource
    private RoleMapper roleMapper;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public User user(String account) {

        User user = userMgrDao.getByAccount(account);

        // アカウント不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // アカウント被凍結
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());            // アカウントid
        shiroUser.setAccount(user.getAccount());// アカウント
        shiroUser.setDeptId(user.getDeptid());    // 部門id
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));// 部門名称
        shiroUser.setName(user.getName());        // 用户名称
        shiroUser.setUserType(user.getUserType());

        Integer[] roleArray = Convert.toIntArray(user.getRoleid().toString());// 役割集合
        shiroUser.setAsyncRouterMapList(this.getAsyncRouterMapList(roleArray[0]));

        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (int roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

//    @Override
//    public List<String> findPermissionsByRoleId(Integer roleId) {
//        List<String> resUrls = menuDao.getResUrlsByRoleId(roleId);
//        return resUrls;
//    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();
        // パスワード加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
    
    private List<RouteConfig> getAsyncRouterMapList(Integer roleId){
    	
    	List<RouteConfig> routerMaplist = null;
    	routerMaplist = roleMapper.getAsyncRouterMapList(roleId);
    	return routerMaplist;
//    	return null;
    	
    }

}
