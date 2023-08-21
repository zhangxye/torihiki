package com.tsrs.webedi.common.constant.dictmap;

import com.tsrs.webedi.common.constant.dictmap.base.AbstractDictMap;

/**
 * 用于削除业务的字典
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-06 15:01
 */
public class DeleteDict extends AbstractDictMap {

    @Override
    public void init() {
        put("roleId","役割名称");
        put("deptId", "部門名称");
        put("menuId", "菜单名称");
        put("dictId", "字典名称");
        put("noticeId", "标题");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("roleId","getCacheObject");
        putFieldWrapperMethodName("deptId","getCacheObject");
        putFieldWrapperMethodName("menuId","getCacheObject");
        putFieldWrapperMethodName("dictId","getCacheObject");
        putFieldWrapperMethodName("noticeId","getCacheObject");

    }
}
