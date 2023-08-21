package com.tsrs.webedi.modular.system.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 見積確定情報
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
public class Estconfirm extends Model<Estconfirm> {

    private static final long serialVersionUID = 1L;

    /**
     * 見積HID
     */
    @TableId("est_hid")
	private Integer estHid;
    /**
     * 見積確認者ID
     */
	@TableField("est_confirm_user_id")
	private String estConfirmUserId;
    /**
     * 見積確認日時
     */
	@TableField("est_confirm_dt")
	private Date estConfirmDt;
    /**
     * 見積確認備考
     */
	@TableField("est_confirm_remark")
	private String estConfirmRemark;
    /**
     * 登録者
     */
	@TableField(value = "sys_ins_user_id", fill = FieldFill.INSERT)
	private String sysInsUserId;
    /**
     * 登録日時
     */
	@TableField(value = "sys_ins_dt", fill = FieldFill.INSERT)
	private Date sysInsDt;
    /**
     * 登録機能ID
     */
	@TableField(value = "sys_ins_pg_id", fill = FieldFill.INSERT)
	private String sysInsPgId;
    /**
     * 更新者
     */
	@TableField(value = "sys_upd_user_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdUserId;
    /**
     * 更新日時
     */
	@TableField(value = "sys_upd_dt", fill = FieldFill.INSERT_UPDATE)
	private Date sysUpdDt;
    /**
     * 更新機能ID
     */
	@TableField(value = "sys_upd_pg_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdPgId;


	public Integer getEstHid() {
		return estHid;
	}

	public void setEstHid(Integer estHid) {
		this.estHid = estHid;
	}

	public String getEstConfirmUserId() {
		return estConfirmUserId;
	}

	public void setEstConfirmUserId(String estConfirmUserId) {
		this.estConfirmUserId = estConfirmUserId;
	}

	public Date getEstConfirmDt() {
		return estConfirmDt;
	}

	public void setEstConfirmDt(Date estConfirmDt) {
		this.estConfirmDt = estConfirmDt;
	}

	public String getEstConfirmRemark() {
		return estConfirmRemark;
	}

	public void setEstConfirmRemark(String estConfirmRemark) {
		this.estConfirmRemark = estConfirmRemark;
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
		return this.estHid;
	}

	@Override
	public String toString() {
		return "Estconfirm{" +
			"estHid=" + estHid +
			", estConfirmUserId=" + estConfirmUserId +
			", estConfirmDt=" + estConfirmDt +
			", estConfirmRemark=" + estConfirmRemark +
			", sysInsUserId=" + sysInsUserId +
			", sysInsDt=" + sysInsDt +
			", sysInsPgId=" + sysInsPgId +
			", sysUpdUserId=" + sysUpdUserId +
			", sysUpdDt=" + sysUpdDt +
			", sysUpdPgId=" + sysUpdPgId +
			"}";
	}
}
