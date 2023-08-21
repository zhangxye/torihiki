package com.tsrs.webedi.modular.system.dao;

import com.tsrs.webedi.common.node.ZTreeNode;
import com.tsrs.webedi.common.persistence.model.LabelValueBean;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 役割相关的dao
 *
 * @author Tsrs-WebEDI
 * @date 2017年2月12日 下午8:43:52
 */
public interface RoleDao {

    /**
     * 根据条件查询役割列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectRoles(@Param("condition") String condition);

    /**
     * 削除某个役割的所有权限
     *
     * @param roleId 役割id
     * @return
     * @date 2017年2月13日 下午7:57:51
     */
    int deleteRolesById(@Param("roleId") Integer roleId);

    /**
     * 获取役割列表树
     *
     * @return
     * @date 2017年2月18日 上午10:32:04
     */
    List<ZTreeNode> roleTreeList();

    /**
     * 获取役割列表树
     *
     * @return
     * @date 2017年2月18日 上午10:32:04
     */
    List<ZTreeNode> roleTreeListByRoleId(String[] roleId);


}
