package com.tsrs.webedi.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.common.persistence.model.UserExt;

/**
 * 管理员的dao
 *
 * @author Tsrs-WebEDI
 * @date 2017年2月12日 下午8:43:52
 */
@Mapper
public interface UserMgrDao {

    /**
     * 修正用户状态
     *
     * @param user
     * @date 2017年2月12日 下午8:42:31
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修正パスワード
     *
     * @param userId
     * @param pwd
     * @date 2017年2月12日 下午8:54:19
     */
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectUsers(@Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("jobTitleCd") String jobTitleCd);

    /**
     * 设置用户的役割
     *
     * @return
     * @date 2017年2月13日 下午7:31:30
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 通过アカウント获取用户
     *
     * @param account
     * @return
     * @date 2017年2月17日 下午11:07:46
     */
    UserExt getByAccount(@Param("account") String account);
    
    
    List<Map<String, Object>> selectActiveUserList(@Param("account") String account);
    
    List<UserExt> selectAllUsers(Map<String, Object> params);
    
    List<LabelValueBean> jobUserList(@Param("jobTitleCd") String jobTitleCd);

}
