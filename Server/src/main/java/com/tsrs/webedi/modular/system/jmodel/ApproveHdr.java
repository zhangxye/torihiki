package com.tsrs.webedi.modular.system.jmodel;

import java.util.List;

public class ApproveHdr {
	private Integer apprDocId;
	private String apply_reason;
	private String confrim_st;
	private String con_rej_comment;
	private boolean save_btn_valid_flg;
	private List<ApproveDtl> apprApplyDtl;
	
	
	public boolean isSave_btn_valid_flg() {
		return save_btn_valid_flg;
	}
	public void setSave_btn_valid_flg(boolean save_btn_valid_flg) {
		this.save_btn_valid_flg = save_btn_valid_flg;
	}
	public Integer getApprDocId() {
		return apprDocId;
	}
	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
	}
	public String getApply_reason() {
		return apply_reason;
	}
	public void setApply_reason(String apply_reason) {
		this.apply_reason = apply_reason;
	}
	public String getConfrim_st() {
		return confrim_st;
	}
	public void setConfrim_st(String confrim_st) {
		this.confrim_st = confrim_st;
	}
	public String getCon_rej_comment() {
		return con_rej_comment;
	}
	public void setCon_rej_comment(String con_rej_comment) {
		this.con_rej_comment = con_rej_comment;
	}
	public List<ApproveDtl> getApprApplyDtl() {
		return apprApplyDtl;
	}
	public void setApprApplyDtl(List<ApproveDtl> apprApplyDtl) {
		this.apprApplyDtl = apprApplyDtl;
	}
	
	
}
