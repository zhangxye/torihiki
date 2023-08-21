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
 * 標準報酬管理
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Basepricemgr extends Model<Basepricemgr> {

    private static final long serialVersionUID = 1L;

    /**
     * 標準報酬管理ID
     */
	@TableId(value="base_price_mgr_id", type= IdType.AUTO)
	private Integer basePriceMgrId;
    /**
     * 標準報酬タイプ:1：健康保険
2：厚生年金
     */
	@TableField("base_price_type")
	private String basePriceType;
    /**
     * 開始日
     */
	@TableField("start_dt")
	private Date startDt;
    /**
     * 終了日:‘2099/12/31’
     */
	@TableField("end_dt")
	private Date endDt;
    /**
     * 報酬月額From
     */
	@TableField("salary_from")
	private BigDecimal salaryFrom;
    /**
     * 報酬月額To(含まれまい)
     */
	@TableField("salary_to")
	private BigDecimal salaryTo;
    /**
     * 標準報酬
     */
	@TableField("base_price")
	private BigDecimal basePrice;
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


	public Integer getBasePriceMgrId() {
		return basePriceMgrId;
	}

	public void setBasePriceMgrId(Integer basePriceMgrId) {
		this.basePriceMgrId = basePriceMgrId;
	}

	public String getBasePriceType() {
		return basePriceType;
	}

	public void setBasePriceType(String basePriceType) {
		this.basePriceType = basePriceType;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public BigDecimal getSalaryFrom() {
		return salaryFrom;
	}

	public void setSalaryFrom(BigDecimal salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public BigDecimal getSalaryTo() {
		return salaryTo;
	}

	public void setSalaryTo(BigDecimal salaryTo) {
		this.salaryTo = salaryTo;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
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
		return this.basePriceMgrId;
	}

}
