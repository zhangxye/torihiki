package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 勤怠管理DTL
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Workhourdtl extends Model<Workhourdtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 勤怠管理DID
     */
	@TableId(value="wh_did", type= IdType.AUTO)
	private Integer whDid;
    /**
     * 勤怠管理HID
     */
	@TableField("wh_hid")
	private Integer whHid;
    /**
     * 年月日
     */
	private Date ymd;
    /**
     * 勤怠区分:1:出勤
2:有休
3:休日
4:休暇
5:代休
6:欠勤
     */
	@TableField("wh_k")
	private String whK;
    /**
     * 作業内容C:出勤の場合、必須
     */
	@TableField("work_content_cd")
	private String workContentCd;
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


	public Integer getWhDid() {
		return whDid;
	}

	public void setWhDid(Integer whDid) {
		this.whDid = whDid;
	}

	public Integer getWhHid() {
		return whHid;
	}

	public void setWhHid(Integer whHid) {
		this.whHid = whHid;
	}

	public Date getYmd() {
		return ymd;
	}

	public void setYmd(Date ymd) {
		this.ymd = ymd;
	}

	public String getWhK() {
		return whK;
	}

	public void setWhK(String whK) {
		this.whK = whK;
	}

	public String getWorkContentCd() {
		return workContentCd;
	}

	public void setWorkContentCd(String workContentCd) {
		this.workContentCd = workContentCd;
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
		return this.whDid;
	}

}
