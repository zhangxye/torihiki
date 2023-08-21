package com.tsrs.webedi.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsrs.webedi.common.persistence.model.Userwfconfirmroute;

/**
 * ユーザーWF承認経路
 *
 * @author Tsrs-WebEDI
 * @date 2017年2月12日 下午8:43:52
 */
public interface UserWfConfirmRouteDao {

    int addUserWfBatch(@Param("list") List<Userwfconfirmroute> list);
    
    List<Userwfconfirmroute> getUserWf(@Param("account") String account);

}
