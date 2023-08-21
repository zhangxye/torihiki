package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * BP見積依頼申請HDR情報
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Bpestreqapplyhdr extends Model<Bpestreqapplyhdr> {

    private static final long serialVersionUID = 1L;

    /**
     * BP見積依頼申請HID
     */
	@TableId(value="bp_est_req_apply_hid", type= IdType.AUTO)
	private Integer bpEstReqApplyHid;
    /**
     * BP見積依頼ID
     */
	@TableField("bp_est_req_id")
	private Integer bpEstReqId;
    /**
     * 使用FLG:Y:使用
N:削除
     */
	@TableField("use_flg")
	private String useFlg;
    /**
     * 申請理由
     */
	@TableField("apply_reason")
	private String applyReason;
    /**
     * 申請者ID
     */
	@TableField("apply_user_id")
	private String applyUserId;
    /**
     * 申請日時
     */
	@TableField("apply_dt")
	private Date applyDt;
    /**
     * 承認待表示順:承認待となっている申請DTLの表示順を設定
     */
	@TableField("confirm_wait_display_order")
	private Integer confirmWaitDisplayOrder;
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


	public Integer getBpEstReqApplyHid() {
		return bpEstReqApplyHid;
	}

	public void setBpEstReqApplyHid(Integer bpEstReqApplyHid) {
		this.bpEstReqApplyHid = bpEstReqApplyHid;
	}

	public Integer getBpEstReqId() {
		return bpEstReqId;
	}

	public void setBpEstReqId(Integer bpEstReqId) {
		this.bpEstReqId = bpEstReqId;
	}

	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}

	public Date getApplyDt() {
		return applyDt;
	}

	public void setApplyDt(Date applyDt) {
		this.applyDt = applyDt;
	}

	public Integer getConfirmWaitDisplayOrder() {
		return confirmWaitDisplayOrder;
	}

	public void setConfirmWaitDisplayOrder(Integer confirmWaitDisplayOrder) {
		this.confirmWaitDisplayOrder = confirmWaitDisplayOrder;
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
		return this.bpEstReqApplyHid;
	}

}
