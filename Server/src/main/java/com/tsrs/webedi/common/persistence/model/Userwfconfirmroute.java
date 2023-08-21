package com.tsrs.webedi.common.persistence.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author tsrs
 * @since 2017-06-20
 */
@TableName("userwfconfirmroute")
public class Userwfconfirmroute extends Model<Userwfconfirmroute> {

    private static final long serialVersionUID = 1L;

    /**
     * ユーザーWF承認経路ID
     */
	@TableId(value="user_wf_confirm_route_id", type= IdType.AUTO)
	private Integer userWfConfirmRouteId;
    /**
     * ユーザーID
     */
	private String user_id;
    /**
     * 承認者ID
     */
	private String accept_user_id;
    /**
     * 表示順
     */
	private Integer display_order;
    /**
     * 登録者
     */
	private String sys_ins_user_id;
   
    /**
     * 登録日時
     */
	private Date sys_ins_dt;
 
    /**
     * 登録機能ID
     */
	private String sys_ins_pg_id;
   
	/**
	 * 更新者
     */
	private String sys_upd_user_id;
   
    /**
     * 更新日時
     */
	private Date sys_upd_dt;
 
    /**
     * 更新機能ID
     */
	private String sys_upd_pg_id;

	
	
	private String accept_user_name;
	private String job_title_name;
	
	
	
	public String getAccept_user_name() {
		return accept_user_name;
	}



	public void setAccept_user_name(String accept_user_name) {
		this.accept_user_name = accept_user_name;
	}



	public String getJob_title_name() {
		return job_title_name;
	}



	public void setJob_title_name(String job_title_name) {
		this.job_title_name = job_title_name;
	}



	public Integer getUserWfConfirmRouteId() {
		return userWfConfirmRouteId;
	}



	public void setUserWfConfirmRouteId(Integer userWfConfirmRouteId) {
		this.userWfConfirmRouteId = userWfConfirmRouteId;
	}



	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getAccept_user_id() {
		return accept_user_id;
	}



	public void setAccept_user_id(String accept_user_id) {
		this.accept_user_id = accept_user_id;
	}



	public Integer getDisplay_order() {
		return display_order;
	}



	public void setDisplay_order(Integer display_order) {
		this.display_order = display_order;
	}



	public String getSys_ins_user_id() {
		return sys_ins_user_id;
	}



	public void setSys_ins_user_id(String sys_ins_user_id) {
		this.sys_ins_user_id = sys_ins_user_id;
	}



	public Date getSys_ins_dt() {
		return sys_ins_dt;
	}



	public void setSys_ins_dt(Date sys_ins_dt) {
		this.sys_ins_dt = sys_ins_dt;
	}



	public String getSys_ins_pg_id() {
		return sys_ins_pg_id;
	}



	public void setSys_ins_pg_id(String sys_ins_pg_id) {
		this.sys_ins_pg_id = sys_ins_pg_id;
	}



	public String getSys_upd_user_id() {
		return sys_upd_user_id;
	}



	public void setSys_upd_user_id(String sys_upd_user_id) {
		this.sys_upd_user_id = sys_upd_user_id;
	}



	public Date getSys_upd_dt() {
		return sys_upd_dt;
	}



	public void setSys_upd_dt(Date sys_upd_dt) {
		this.sys_upd_dt = sys_upd_dt;
	}



	public String getSys_upd_pg_id() {
		return sys_upd_pg_id;
	}



	public void setSys_upd_pg_id(String sys_upd_pg_id) {
		this.sys_upd_pg_id = sys_upd_pg_id;
	}



	@Override
	public Serializable pkVal() {
		return this.userWfConfirmRouteId;
	}

}
