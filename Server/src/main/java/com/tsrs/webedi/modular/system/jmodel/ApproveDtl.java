package com.tsrs.webedi.modular.system.jmodel;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ApproveDtl {

	private Integer display_order;
	private String accept_user_name;
	private String job_title_name;
	private String confirm_st_name;
	@JSONField (format="yyyy-MM-dd") 
	private Date con_rej_dt;
	private String con_rej_comment;
	public Integer getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(Integer display_order) {
		this.display_order = display_order;
	}
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
	public String getConfirm_st_name() {
		return confirm_st_name;
	}
	public void setConfirm_st_name(String confirm_st_name) {
		this.confirm_st_name = confirm_st_name;
	}
	public Date getCon_rej_dt() {
		return con_rej_dt;
	}
	public void setCon_rej_dt(Date con_rej_dt) {
		this.con_rej_dt = con_rej_dt;
	}
	public String getCon_rej_comment() {
		return con_rej_comment;
	}
	public void setCon_rej_comment(String con_rej_comment) {
		this.con_rej_comment = con_rej_comment;
	}
	
	
}
