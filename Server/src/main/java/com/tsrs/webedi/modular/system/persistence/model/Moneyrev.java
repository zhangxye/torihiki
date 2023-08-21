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
 * 入金
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Moneyrev extends Model<Moneyrev> {

    private static final long serialVersionUID = 1L;

    /**
     * 入金ID
     */
	@TableId(value="money_rec_id", type= IdType.AUTO)
	private Integer moneyRecId;
    /**
     * 入金日
     */
	@TableField("req_dt")
	private Date reqDt;
    /**
     * 入金金額
     */
	@TableField("money_rec_amt")
	private BigDecimal moneyRecAmt;
    /**
     * 入金備考
     */
	@TableField("money_rec_remark")
	private String moneyRecRemark;
    /**
     * 請求ID
     */
	@TableField("req_id")
	private Integer reqId;
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


	public Integer getMoneyRecId() {
		return moneyRecId;
	}

	public void setMoneyRecId(Integer moneyRecId) {
		this.moneyRecId = moneyRecId;
	}

	public Date getReqDt() {
		return reqDt;
	}

	public void setReqDt(Date reqDt) {
		this.reqDt = reqDt;
	}

	public BigDecimal getMoneyRecAmt() {
		return moneyRecAmt;
	}

	public void setMoneyRecAmt(BigDecimal moneyRecAmt) {
		this.moneyRecAmt = moneyRecAmt;
	}

	public String getMoneyRecRemark() {
		return moneyRecRemark;
	}

	public void setMoneyRecRemark(String moneyRecRemark) {
		this.moneyRecRemark = moneyRecRemark;
	}

	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
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
		return this.moneyRecId;
	}

}
