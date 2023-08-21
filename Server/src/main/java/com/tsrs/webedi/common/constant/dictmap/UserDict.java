package com.tsrs.webedi.common.constant.dictmap;

import com.tsrs.webedi.common.constant.dictmap.base.AbstractDictMap;

/**
 * 用户的字典
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-06 15:01
 */
public class UserDict extends AbstractDictMap {

    @Override
    public void init() {
        put("userId","アカウント");
        put("avatar","头像");
        put("account","アカウント");
        put("name","名字");
        put("birthday","生日");
        put("sex","性別");
        put("email","电子邮件");
        put("phone","電話");
        put("roleid","役割名称");
        put("deptid","部門名称");
        put("roleIds","役割名称集合");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("sex","getSexName");
        putFieldWrapperMethodName("deptid","getDeptName");
        putFieldWrapperMethodName("roleid","getSingleRoleName");
        putFieldWrapperMethodName("userId","getUserAccountById");
        putFieldWrapperMethodName("roleIds","getRoleName");
    }
}
