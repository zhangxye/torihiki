package com.tsrs.webedi.common.constant.dictmap;

import com.tsrs.webedi.common.constant.dictmap.base.AbstractDictMap;

/**
 * 部門的映射
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-06 15:01
 */
public class DeptDict extends AbstractDictMap {

    @Override
    public void init() {
        put("deptId", "部門名称");
        put("num", "部門排序");
        put("pid", "上级名称");
        put("simplename", "部門简称");
        put("fullname", "部門全称");
        put("tips", "备注");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("deptId", "getDeptName");
        putFieldWrapperMethodName("pid", "getDeptName");
    }
}
