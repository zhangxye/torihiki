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
 * BP発注情報
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Bpshippingorder extends Model<Bpshippingorder> {

    private static final long serialVersionUID = 1L;

    /**
     * BP発注ID
     */
	@TableId(value="bp_shipping_order_id", type= IdType.AUTO)
	private Integer bpShippingOrderId;
    /**
     * BP発注NO
     */
	@TableField("bp_shipping_order_no")
	private String bpShippingOrderNo;
    /**
     * BP見積ID
     */
	@TableField("bp_estimation_id")
	private Integer bpEstimationId;
    /**
     * BP見積NO
     */
	@TableField("bp_estimation_no")
	private String bpEstimationNo;
    /**
     * BP見積依頼ID
     */
	@TableField("bp_estimation_request_id")
	private Integer bpEstimationRequestId;
    /**
     * BP見積依頼NO
     */
	@TableField("bp_estimation_request_no")
	private String bpEstimationRequestNo;
    /**
     * 使用FLG:Y/N
     */
	@TableField("use_flg")
	private String useFlg;
    /**
     * 受注HID
     */
	@TableField("order_hid")
	private Integer orderHid;
    /**
     * 受注金額(税抜)
     */
	@TableField("order_amt_without_tax")
	private BigDecimal orderAmtWithoutTax;
    /**
     * 作業開始日
     */
	@TableField("work_start_ymd")
	private Date workStartYmd;
    /**
     * 作業終了日
     */
	@TableField("work_end_ymd")
	private Date workEndYmd;
    /**
     * 案件番号
     */
	@TableField("project_no")
	private String projectNo;
    /**
     * 案件名称
     */
	@TableField("project_nm")
	private String projectNm;
    /**
     * 作業内容
     */
	@TableField("work_content")
	private String workContent;
    /**
     * 作業場所
     */
	@TableField("work_place")
	private String workPlace;
    /**
     * 注文内容
     */
	@TableField("order_content")
	private String orderContent;
    /**
     * 特記事項
     */
	@TableField("order_special_affairs")
	private String orderSpecialAffairs;
    /**
     * 備考
     */
	@TableField("order_remark")
	private String orderRemark;
    /**
     * 注文書
     */
	@TableField("order_doc_file_id")
	private Integer orderDocFileId;
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


	public Integer getBpShippingOrderId() {
		return bpShippingOrderId;
	}

	public void setBpShippingOrderId(Integer bpShippingOrderId) {
		this.bpShippingOrderId = bpShippingOrderId;
	}

	public String getBpShippingOrderNo() {
		return bpShippingOrderNo;
	}

	public void setBpShippingOrderNo(String bpShippingOrderNo) {
		this.bpShippingOrderNo = bpShippingOrderNo;
	}

	public Integer getBpEstimationId() {
		return bpEstimationId;
	}

	public void setBpEstimationId(Integer bpEstimationId) {
		this.bpEstimationId = bpEstimationId;
	}

	public String getBpEstimationNo() {
		return bpEstimationNo;
	}

	public void setBpEstimationNo(String bpEstimationNo) {
		this.bpEstimationNo = bpEstimationNo;
	}

	public Integer getBpEstimationRequestId() {
		return bpEstimationRequestId;
	}

	public void setBpEstimationRequestId(Integer bpEstimationRequestId) {
		this.bpEstimationRequestId = bpEstimationRequestId;
	}

	public String getBpEstimationRequestNo() {
		return bpEstimationRequestNo;
	}

	public void setBpEstimationRequestNo(String bpEstimationRequestNo) {
		this.bpEstimationRequestNo = bpEstimationRequestNo;
	}

	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	public Integer getOrderHid() {
		return orderHid;
	}

	public void setOrderHid(Integer orderHid) {
		this.orderHid = orderHid;
	}

	public BigDecimal getOrderAmtWithoutTax() {
		return orderAmtWithoutTax;
	}

	public void setOrderAmtWithoutTax(BigDecimal orderAmtWithoutTax) {
		this.orderAmtWithoutTax = orderAmtWithoutTax;
	}

	public Date getWorkStartYmd() {
		return workStartYmd;
	}

	public void setWorkStartYmd(Date workStartYmd) {
		this.workStartYmd = workStartYmd;
	}

	public Date getWorkEndYmd() {
		return workEndYmd;
	}

	public void setWorkEndYmd(Date workEndYmd) {
		this.workEndYmd = workEndYmd;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectNm() {
		return projectNm;
	}

	public void setProjectNm(String projectNm) {
		this.projectNm = projectNm;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getOrderSpecialAffairs() {
		return orderSpecialAffairs;
	}

	public void setOrderSpecialAffairs(String orderSpecialAffairs) {
		this.orderSpecialAffairs = orderSpecialAffairs;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Integer getOrderDocFileId() {
		return orderDocFileId;
	}

	public void setOrderDocFileId(Integer orderDocFileId) {
		this.orderDocFileId = orderDocFileId;
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
		return this.bpShippingOrderId;
	}

}
