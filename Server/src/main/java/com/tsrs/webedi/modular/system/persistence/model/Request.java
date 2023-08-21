package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 請求
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Request extends Model<Request> {

    private static final long serialVersionUID = 1L;

    /**
     * 請求ID
     */
	@TableId(value="req_id", type= IdType.AUTO)
	private Integer reqId;
    /**
     * 請求日
     */
	@TableField("req_dt")
	private Date reqDt;
    /**
     * 請求担当
     */
	@TableField("req_user_id")
	private String reqUserId;
    /**
     * 請求金額（税抜）:請求金額（税抜）の合計
     */
	@TableField("req_amt_without_tax")
	private BigDecimal reqAmtWithoutTax;
    /**
     * 消費税
     */
	@TableField("req_amt_tax")
	private BigDecimal reqAmtTax;
    /**
     * 請求金額（税込）
     */
	@TableField("req_amt_with_tax")
	private BigDecimal reqAmtWithTax;
    /**
     * 請求備考
     */
	@TableField("req_remark")
	private String reqRemark;
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


	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	public Date getReqDt() {
		return reqDt;
	}

	public void setReqDt(Date reqDt) {
		this.reqDt = reqDt;
	}

	public String getReqUserId() {
		return reqUserId;
	}

	public void setReqUserId(String reqUserId) {
		this.reqUserId = reqUserId;
	}

	public BigDecimal getReqAmtWithoutTax() {
		return reqAmtWithoutTax;
	}

	public void setReqAmtWithoutTax(BigDecimal reqAmtWithoutTax) {
		this.reqAmtWithoutTax = reqAmtWithoutTax;
	}

	public BigDecimal getReqAmtTax() {
		return reqAmtTax;
	}

	public void setReqAmtTax(BigDecimal reqAmtTax) {
		this.reqAmtTax = reqAmtTax;
	}

	public BigDecimal getReqAmtWithTax() {
		return reqAmtWithTax;
	}

	public void setReqAmtWithTax(BigDecimal reqAmtWithTax) {
		this.reqAmtWithTax = reqAmtWithTax;
	}

	public String getReqRemark() {
		return reqRemark;
	}

	public void setReqRemark(String reqRemark) {
		this.reqRemark = reqRemark;
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
		return this.reqId;
	}

}
