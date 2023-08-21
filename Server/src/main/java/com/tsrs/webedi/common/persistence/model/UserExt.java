package com.tsrs.webedi.common.persistence.model;

import java.util.List;

public class UserExt extends User {

	private String job_title_nm;
	private String role_nm;
	private String user_type_nm;
	private String last_login_dt_str;
	private String status_nm;
	private List<Userwfconfirmroute> userwfconfirmroute;
	public List<Userwfconfirmroute> getUserwfconfirmroute() {
		return userwfconfirmroute;
	}

	public void setUserwfconfirmroute(List<Userwfconfirmroute> userwfconfirmroute) {
		this.userwfconfirmroute = userwfconfirmroute;
	}

	public String getJob_title_nm() {
		return job_title_nm;
	}

	public void setJob_title_nm(String job_title_nm) {
		this.job_title_nm = job_title_nm;
	}

	public String getRole_nm() {
		return role_nm;
	}

	public void setRole_nm(String role_nm) {
		this.role_nm = role_nm;
	}

	public String getUser_type_nm() {
		return user_type_nm;
	}

	public void setUser_type_nm(String user_type_nm) {
		this.user_type_nm = user_type_nm;
	}

	public String getLast_login_dt_str() {
		return last_login_dt_str;
	}

	public void setLast_login_dt_str(String last_login_dt_str) {
		this.last_login_dt_str = last_login_dt_str;
	}

	public String getStatus_nm() {
		return status_nm;
	}

	public void setStatus_nm(String status_nm) {
		this.status_nm = status_nm;
	}
	

	
}
