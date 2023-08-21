package com.tsrs.webedi.common.persistence.dao;

import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.common.persistence.model.MenuTree;
import com.tsrs.webedi.common.persistence.model.Role;
import com.tsrs.webedi.common.persistence.model.RouteConfig;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
  * ロール Mapper 接口
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public interface RoleMapper extends BaseMapper<Role> {
	
    public List<LabelValueBean> selectAllRoles();
	
	public List<RouteConfig> getAsyncRouterMapList(@Param("role_id") Integer roleId);
	
	public List<MenuTree> getMenuTreeList(@Param("role_id") Integer roleId);
	
	public List<Map<String, Object>> getRoleList(@Param("role_id") Integer roleId, @Param("role_nm") String roleNm);
	
	public Map<String, Object> getRoleInfo(@Param("role_id") Integer roleId);
	
	public List<Map<String, Object>> selectMenuList(@Param("role_id") Integer roleId);
	
	public List<String> selectPgList2(@Param("menu_id") Integer menuId);
	
	@Update("UPDATE role SET name= #{name} WHERE id=#{id}")
	public int updRoleData(Role role);

}