package com.tsrs.webedi.modular.system.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 売上_原価明細
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Salescostdtl extends Model<Salescostdtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 売上ID
     */
    @TableId("sales_id")
	private Integer salesId;
    /**
     * 締日
     */
	@TableField("payment_dat")
	private Date paymentDat;
    /**
     * 作業案件名称
     */
	@TableField("pj_nm")
	private String pjNm;
    /**
     * 所属会社ID
     */
	@TableField("bp_comp_id")
	private String bpCompId;
    /**
     * 所属会社名/社員氏名
     */
	@TableField("bp_comp_emp_nm")
	private String bpCompEmpNm;
    /**
     * 社保加算後単価:加入の場合、単価＋交通費＋（健康保険+厚生年金）＊1.0085
加入しない場合、単価＋交通費
     */
	@TableField("bp_cost_price")
	private BigDecimal bpCostPrice;
    /**
     * 社保加算金額（税抜）:社保加算後単価＊工数（月次用）
     */
	@TableField("bp_cost_amt_without_tax")
	private BigDecimal bpCostAmtWithoutTax;
    /**
     * 消費税
     */
	@TableField("bp_cost_tax")
	private BigDecimal bpCostTax;
    /**
     * 社保加算金額（税込）
     */
	@TableField("bp_cost_amt_with_tax")
	private BigDecimal bpCostAmtWithTax;
    /**
     * 支払サイト/出金日付の日
     */
	@TableField("payment_plan_d")
	private Integer paymentPlanD;
    /**
     * 支払サイト/締日区分
     */
	@TableField("payment_due_k")
	private String paymentDueK;
    /**
     * 支払サイト/支払月区分
     */
	@TableField("pay_month_k")
	private String payMonthK;
    /**
     * 支払サイト/支払日付の日
     */
	@TableField("pay_plan_d")
	private Integer payPlanD;
    /**
     * 出金予定日
     */
	@TableField("salary_pay_plan_dt")
	private Date salaryPayPlanDt;
    /**
     * 売上原価備考
     */
	@TableField("sales_cost_remark")
	private String salesCostRemark;
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


	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public Date getPaymentDat() {
		return paymentDat;
	}

	public void setPaymentDat(Date paymentDat) {
		this.paymentDat = paymentDat;
	}

	public String getPjNm() {
		return pjNm;
	}

	public void setPjNm(String pjNm) {
		this.pjNm = pjNm;
	}

	public String getBpCompId() {
		return bpCompId;
	}

	public void setBpCompId(String bpCompId) {
		this.bpCompId = bpCompId;
	}

	public String getBpCompEmpNm() {
		return bpCompEmpNm;
	}

	public void setBpCompEmpNm(String bpCompEmpNm) {
		this.bpCompEmpNm = bpCompEmpNm;
	}

	public BigDecimal getBpCostPrice() {
		return bpCostPrice;
	}

	public void setBpCostPrice(BigDecimal bpCostPrice) {
		this.bpCostPrice = bpCostPrice;
	}

	public BigDecimal getBpCostAmtWithoutTax() {
		return bpCostAmtWithoutTax;
	}

	public void setBpCostAmtWithoutTax(BigDecimal bpCostAmtWithoutTax) {
		this.bpCostAmtWithoutTax = bpCostAmtWithoutTax;
	}

	public BigDecimal getBpCostTax() {
		return bpCostTax;
	}

	public void setBpCostTax(BigDecimal bpCostTax) {
		this.bpCostTax = bpCostTax;
	}

	public BigDecimal getBpCostAmtWithTax() {
		return bpCostAmtWithTax;
	}

	public void setBpCostAmtWithTax(BigDecimal bpCostAmtWithTax) {
		this.bpCostAmtWithTax = bpCostAmtWithTax;
	}

	public Integer getPaymentPlanD() {
		return paymentPlanD;
	}

	public void setPaymentPlanD(Integer paymentPlanD) {
		this.paymentPlanD = paymentPlanD;
	}

	public String getPaymentDueK() {
		return paymentDueK;
	}

	public void setPaymentDueK(String paymentDueK) {
		this.paymentDueK = paymentDueK;
	}

	public String getPayMonthK() {
		return payMonthK;
	}

	public void setPayMonthK(String payMonthK) {
		this.payMonthK = payMonthK;
	}

	public Integer getPayPlanD() {
		return payPlanD;
	}

	public void setPayPlanD(Integer payPlanD) {
		this.payPlanD = payPlanD;
	}

	public Date getSalaryPayPlanDt() {
		return salaryPayPlanDt;
	}

	public void setSalaryPayPlanDt(Date salaryPayPlanDt) {
		this.salaryPayPlanDt = salaryPayPlanDt;
	}

	public String getSalesCostRemark() {
		return salesCostRemark;
	}

	public void setSalesCostRemark(String salesCostRemark) {
		this.salesCostRemark = salesCostRemark;
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
		return this.salesId;
	}

}
