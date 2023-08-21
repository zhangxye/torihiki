package com.tsrs.webedi.modular.system.persistence.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 承認済稟議書
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Apprdocaccepted extends Model<Apprdocaccepted> {

    private static final long serialVersionUID = 1L;

    /**
     * 稟議書ID
     */
    @TableId("appr_doc_id")
	private Integer apprDocId;
    /**
     * 最終承認者ID
     */
	@TableField("last_accept_user_id")
	private String lastAcceptUserId;
    /**
     * 最終承認日時
     */
	@TableField("last_accept_date")
	private Date lastAcceptDate;
    /**
     * 稟議書申請HID
     */
	@TableField("approval_apply_hid")
	private Integer approvalApplyHid;
    /**
     * 登録者
     */
	@TableField("sys_ins_user_id")
	private String sysInsUserId;
    /**
     * 登録日時
     */
	@TableField("sys_ins_dt")
	private Date sysInsDt;
    /**
     * 登録機能ID
     */
	@TableField("sys_ins_pg_id")
	private String sysInsPgId;
    /**
     * 更新者
     */
	@TableField("sys_upd_user_id")
	private String sysUpdUserId;
    /**
     * 更新日時
     */
	@TableField("sys_upd_dt")
	private Date sysUpdDt;
    /**
     * 更新機能ID
     */
	@TableField("sys_upd_pg_id")
	private String sysUpdPgId;


	public Integer getApprDocId() {
		return apprDocId;
	}

	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
	}

	public String getLastAcceptUserId() {
		return lastAcceptUserId;
	}

	public void setLastAcceptUserId(String lastAcceptUserId) {
		this.lastAcceptUserId = lastAcceptUserId;
	}

	public Date getLastAcceptDate() {
		return lastAcceptDate;
	}

	public void setLastAcceptDate(Date lastAcceptDate) {
		this.lastAcceptDate = lastAcceptDate;
	}

	public Integer getApprovalApplyHid() {
		return approvalApplyHid;
	}

	public void setApprovalApplyHid(Integer approvalApplyHid) {
		this.approvalApplyHid = approvalApplyHid;
	}

	public String getSysInsUserId() {
		return sysInsUserId;
	}

	public void setSysInsUserId(String sysInsUserId) {
		this.sysInsUserId = sysInsUserId;
	}

	public Date getSysInsDt() {
		return sysInsDt;
	}

	public void setSysInsDt(Date sysInsDt) {
		this.sysInsDt = sysInsDt;
	}

	public String getSysInsPgId() {
		return sysInsPgId;
	}

	public void setSysInsPgId(String sysInsPgId) {
		this.sysInsPgId = sysInsPgId;
	}

	public String getSysUpdUserId() {
		return sysUpdUserId;
	}

	public void setSysUpdUserId(String sysUpdUserId) {
		this.sysUpdUserId = sysUpdUserId;
	}

	public Date getSysUpdDt() {
		return sysUpdDt;
	}

	public void setSysUpdDt(Date sysUpdDt) {
		this.sysUpdDt = sysUpdDt;
	}

	public String getSysUpdPgId() {
		return sysUpdPgId;
	}

	public void setSysUpdPgId(String sysUpdPgId) {
		this.sysUpdPgId = sysUpdPgId;
	}

	@Override
	public Serializable pkVal() {
		return this.apprDocId;
	}

}
