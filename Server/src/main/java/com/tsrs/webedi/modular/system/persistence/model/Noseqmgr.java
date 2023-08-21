package com.tsrs.webedi.modular.system.persistence.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 採番管理
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Noseqmgr extends Model<Noseqmgr> {

    private static final long serialVersionUID = 1L;

    /**
     * システムC
     */
    @TableId("sys_cd")
	private String sysCd;
    /**
     * テーブルID
     */
	@TableField("sys_tbl_id")
	private String sysTblId;
    /**
     * フィールドID
     */
	@TableField("sys_field")
	private String sysField;
    /**
     * 接頭辞
     */
	@TableField("sys_prefix")
	private String sysPrefix;
    /**
     * 年月
     */
	@TableField("sys_ym")
	private String sysYm;
    /**
     * 開始NO
     */
	@TableField("sys_seq_start_no")
	private Integer sysSeqStartNo;
    /**
     * 終了NO
     */
	@TableField("sys_seq_end_no")
	private Integer sysSeqEndNo;
    /**
     * カレントNO
     */
	@TableField("sys_current_no")
	private Integer sysCurrentNo;
    /**
     * 備考
     */
	@TableField("sys_field_remark")
	private String sysFieldRemark;
    /**
     * 登録者
     */
	@TableField(value="sys_ins_user_id", fill = FieldFill.INSERT)
	private String sysInsUserId;
    /**
     * 登録日時
     */
	@TableField(value="sys_ins_dt", fill = FieldFill.INSERT)
	private Date sysInsDt;
    /**
     * 登録機能ID
     */
	@TableField(value="sys_ins_pg_id", fill = FieldFill.INSERT)
	private String sysInsPgId;
    /**
     * 更新者
     */
	@TableField(value="sys_upd_user_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdUserId;
    /**
     * 更新日時
     */
	@TableField(value="sys_upd_dt", fill = FieldFill.INSERT_UPDATE)
	private Date sysUpdDt;
    /**
     * 更新機能ID
     */
	@TableField(value="sys_upd_pg_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdPgId;


	public String getSysCd() {
		return sysCd;
	}

	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}

	public String getSysTblId() {
		return sysTblId;
	}

	public void setSysTblId(String sysTblId) {
		this.sysTblId = sysTblId;
	}

	public String getSysField() {
		return sysField;
	}

	public void setSysField(String sysField) {
		this.sysField = sysField;
	}

	public String getSysPrefix() {
		return sysPrefix;
	}

	public void setSysPrefix(String sysPrefix) {
		this.sysPrefix = sysPrefix;
	}

	public String getSysYm() {
		return sysYm;
	}

	public void setSysYm(String sysYm) {
		this.sysYm = sysYm;
	}

	public Integer getSysSeqStartNo() {
		return sysSeqStartNo;
	}

	public void setSysSeqStartNo(Integer sysSeqStartNo) {
		this.sysSeqStartNo = sysSeqStartNo;
	}

	public Integer getSysSeqEndNo() {
		return sysSeqEndNo;
	}

	public void setSysSeqEndNo(Integer sysSeqEndNo) {
		this.sysSeqEndNo = sysSeqEndNo;
	}

	public Integer getSysCurrentNo() {
		return sysCurrentNo;
	}

	public void setSysCurrentNo(Integer sysCurrentNo) {
		this.sysCurrentNo = sysCurrentNo;
	}

	public String getSysFieldRemark() {
		return sysFieldRemark;
	}

	public void setSysFieldRemark(String sysFieldRemark) {
		this.sysFieldRemark = sysFieldRemark;
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
		return this.sysCd;
	}

}
