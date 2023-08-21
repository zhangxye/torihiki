package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * BP見積依頼DTL情報
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Bpestreqdtl extends Model<Bpestreqdtl> {

    private static final long serialVersionUID = 1L;

    /**
     * BP見積依頼DID
     */
	@TableId(value="bp_est_req_did", type= IdType.AUTO)
	private Integer bpEstReqDid;
    /**
     * BP見積依頼HID
     */
	@TableField("bp_est_req_hid")
	private Integer bpEstReqHid;
    /**
     * 稟議書ID
     */
	@TableField("appr_doc_id")
	private Integer apprDocId;
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


	public Integer getBpEstReqDid() {
		return bpEstReqDid;
	}

	public void setBpEstReqDid(Integer bpEstReqDid) {
		this.bpEstReqDid = bpEstReqDid;
	}

	public Integer getBpEstReqHid() {
		return bpEstReqHid;
	}

	public void setBpEstReqHid(Integer bpEstReqHid) {
		this.bpEstReqHid = bpEstReqHid;
	}

	public Integer getApprDocId() {
		return apprDocId;
	}

	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
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
		return this.bpEstReqDid;
	}

}
