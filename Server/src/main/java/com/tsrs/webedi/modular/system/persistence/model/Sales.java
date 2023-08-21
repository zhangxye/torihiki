package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 売上
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Sales extends Model<Sales> {

    private static final long serialVersionUID = 1L;

    /**
     * 売上ID
     */
	@TableId(value="sales_id", type= IdType.AUTO)
	private Integer salesId;
    /**
     * 受注HID
     */
	@TableField("order_hid")
	private Integer orderHid;
    /**
     * 受注DID
     */
	@TableField("order_did")
	private Integer orderDid;
    /**
     * 稟議書ID
     */
	@TableField("appr_doc_id")
	private Integer apprDocId;
    /**
     * 計上年月
     */
	@TableField("add_up_ym")
	private String addUpYm;
    /**
     * 計上日
     */
	@TableField("add_up_dt")
	private Date addUpDt;
    /**
     * 要員区分/補助:1：新規要員
2：既存要員
3：要員退社
     */
	@TableField("emp_k")
	private String empK;
    /**
     * 社員番号
     */
	@TableField("emp_no")
	private String empNo;
    /**
     * 社員名
     */
	@TableField("emp_nm")
	private String empNm;
    /**
     * 社員名(フリガナ)
     */
	@TableField("emp_nm_fuli")
	private String empNmFuli;
    /**
     * 営業主担当
     */
	@TableField("bussiness_main_user_id")
	private String bussinessMainUserId;
    /**
     * 売上登録備考
     */
	@TableField("sales_remark")
	private String salesRemark;
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

	public Integer getOrderHid() {
		return orderHid;
	}

	public void setOrderHid(Integer orderHid) {
		this.orderHid = orderHid;
	}

	public Integer getOrderDid() {
		return orderDid;
	}

	public void setOrderDid(Integer orderDid) {
		this.orderDid = orderDid;
	}

	public Integer getApprDocId() {
		return apprDocId;
	}

	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
	}

	public String getAddUpYm() {
		return addUpYm;
	}

	public void setAddUpYm(String addUpYm) {
		this.addUpYm = addUpYm;
	}

	public Date getAddUpDt() {
		return addUpDt;
	}

	public void setAddUpDt(Date addUpDt) {
		this.addUpDt = addUpDt;
	}

	public String getEmpK() {
		return empK;
	}

	public void setEmpK(String empK) {
		this.empK = empK;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpNm() {
		return empNm;
	}

	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}

	public String getEmpNmFuli() {
		return empNmFuli;
	}

	public void setEmpNmFuli(String empNmFuli) {
		this.empNmFuli = empNmFuli;
	}

	public String getBussinessMainUserId() {
		return bussinessMainUserId;
	}

	public void setBussinessMainUserId(String bussinessMainUserId) {
		this.bussinessMainUserId = bussinessMainUserId;
	}

	public String getSalesRemark() {
		return salesRemark;
	}

	public void setSalesRemark(String salesRemark) {
		this.salesRemark = salesRemark;
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
