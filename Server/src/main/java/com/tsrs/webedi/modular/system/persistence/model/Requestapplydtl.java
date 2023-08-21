package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 請求申請DTL情報
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Requestapplydtl extends Model<Requestapplydtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 請求申請DID
     */
	@TableId(value="req_apply_did", type= IdType.AUTO)
	private Integer reqApplyDid;
    /**
     * 請求申請HID
     */
	@TableField("req_apply_hid")
	private Integer reqApplyHid;
    /**
     * 表示順
     */
	@TableField("display_order")
	private Integer displayOrder;
    /**
     * 承認者ID
     */
	@TableField("accept_user_id")
	private String acceptUserId;
    /**
     * 承認状況:1：未承認
2：承認済
3：拒否
     */
	@TableField("confirm_st")
	private String confirmSt;
    /**
     * 承認(拒否)コメント
     */
	@TableField("con_rej_comment")
	private String conRejComment;
    /**
     * 承認(拒否)日時
     */
	@TableField("con_rej_dt")
	private Date conRejDt;
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


	public Integer getReqApplyDid() {
		return reqApplyDid;
	}

	public void setReqApplyDid(Integer reqApplyDid) {
		this.reqApplyDid = reqApplyDid;
	}

	public Integer getReqApplyHid() {
		return reqApplyHid;
	}

	public void setReqApplyHid(Integer reqApplyHid) {
		this.reqApplyHid = reqApplyHid;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getAcceptUserId() {
		return acceptUserId;
	}

	public void setAcceptUserId(String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}

	public String getConfirmSt() {
		return confirmSt;
	}

	public void setConfirmSt(String confirmSt) {
		this.confirmSt = confirmSt;
	}

	public String getConRejComment() {
		return conRejComment;
	}

	public void setConRejComment(String conRejComment) {
		this.conRejComment = conRejComment;
	}

	public Date getConRejDt() {
		return conRejDt;
	}

	public void setConRejDt(Date conRejDt) {
		this.conRejDt = conRejDt;
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
		return this.reqApplyDid;
	}

}
