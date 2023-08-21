package com.tsrs.webedi.modular.system.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.common.persistence.model.User;
import com.tsrs.webedi.common.persistence.model.UserExt;

public interface IUserService {
	 /**
     * 新規ユーザ
     *
     * @author Tsrs-WebEDI
     * @Date 2017/4/27 17:01
     */
    void addUser(UserExt user) throws IllegalAccessException, InvocationTargetException;
    
    void updateUser(UserExt user);
    
    List<UserExt> search(String username,String jobTitleCd);
    
    UserExt getUserInfo(String account);
    
    List<Map<String, Object>> selectActiveUserList(String account);
    
    Integer updateById(User user);
    
    List<LabelValueBean> jobUserList(String jobTitleCd);
    

}
