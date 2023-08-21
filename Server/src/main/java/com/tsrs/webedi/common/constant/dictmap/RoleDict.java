package com.tsrs.webedi.common.constant.dictmap;

import com.tsrs.webedi.common.constant.dictmap.base.AbstractDictMap;

/**
 * 役割的字典
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-06 15:01
 */
public class RoleDict extends AbstractDictMap {

    @Override
    public void init() {
        put("roleId","役割名称");
        put("num","役割排序");
        put("pid","役割的父级");
        put("name","役割名称");
        put("deptid","部門名称");
        put("tips","备注");
        put("ids","资源名称");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("pid","getSingleRoleName");
        putFieldWrapperMethodName("deptid","getDeptName");
        putFieldWrapperMethodName("roleId","getSingleRoleName");
        putFieldWrapperMethodName("ids","getMenuNames");
    }
}
