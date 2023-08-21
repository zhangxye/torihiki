package com.tsrs.webedi.core.shiro;

import com.tsrs.webedi.common.persistence.model.RouteConfig;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 *
 * @author Tsrs-WebEDI
 * @date 2016年12月5日 上午10:26:43
 */
public class ShiroUser implements Serializable {

//    @Serial
    private static final long serialVersionUID = 1L;

    public Integer id;          // 主键ID
    public String account;      // アカウント
    public String name;         // 名前
    public Integer deptId;      // 部門id
    public List<Integer> roleList; // 役割集
    public String deptName;        // 部門名称
    public List<String> roleNames; // 役割名称集
    private String userType;        //ユーザータイプ
    private String token;        //トークン
    
    //メニューリスト
    private List<RouteConfig> asyncRouterMapList;
//    private String asyncRouterMapList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<RouteConfig> getAsyncRouterMapList() {
        return asyncRouterMapList;
    }

    public void setAsyncRouterMapList(List<RouteConfig> asyncRouterMapList) {
        this.asyncRouterMapList = asyncRouterMapList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
