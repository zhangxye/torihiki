package com.tsrs.webedi.modular.system.dao;

import com.tsrs.webedi.common.node.ZTreeNode;
import com.tsrs.webedi.common.persistence.model.LabelValueBean;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部門dao
 *
 * @author Tsrs-WebEDI
 * @date 2017年2月17日20:28:58
 */
public interface DeptDao {

    /**
     * 获取ztree的节点列表
     *
     * @return
     * @date 2017年2月17日 下午8:28:43
     */
    List<ZTreeNode> tree();

    List<Map<String, Object>> list(@Param("condition") String condition);
    
    List<LabelValueBean> list2();
}
