package com.tsrs.webedi.modular.system.persistence.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * クライアント契約契約条件エビデンス
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public class Clientcontractevidence extends Model<Clientcontractevidence> {

    private static final long serialVersionUID = 1L;

    /**
     * エビデンスID
     */
    @TableId(value="appr_client_contract_evidence_d", type= IdType.AUTO)
	private Integer clientContractEvidenceD;
    /**
     * 稟議書ID
     */
	@TableField("appr_doc_id")
	private Integer apprDocId;
    /**
     * 表示順
     */
	@TableField("display_order")
	private Integer displayOrder;
    /**
     * エビデンス
     */
	@TableField("work_evidence")
	private String workEvidence;
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


	public Integer getClientContractEvidenceD() {
		return clientContractEvidenceD;
	}

	public void setClientContractEvidenceD(Integer clientContractEvidenceD) {
		this.clientContractEvidenceD = clientContractEvidenceD;
	}

	public Integer getApprDocId() {
		return apprDocId;
	}

	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getWorkEvidence() {
		return workEvidence;
	}

	public void setWorkEvidence(String workEvidence) {
		this.workEvidence = workEvidence;
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
		return this.clientContractEvidenceD;
	}

}
