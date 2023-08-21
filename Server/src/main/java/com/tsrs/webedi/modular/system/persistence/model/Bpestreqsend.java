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
 * BP見積依頼送信情報
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Bpestreqsend extends Model<Bpestreqsend> {

    private static final long serialVersionUID = 1L;

    /**
     * BP見積依頼送信ID
     */
	@TableId(value="bp_est_req_send_id", type= IdType.AUTO)
	private Integer bpEstReqSendId;
    /**
     * 送信日時
     */
	@TableField("send_dt")
	private Date sendDt;
    /**
     * BP見積依頼ID
     */
	@TableField("bp_est_req_id")
	private Integer bpEstReqId;
    /**
     * BP見積依頼NO
     */
	@TableField("bp_est_req_no")
	private String bpEstReqNo;
    /**
     * 使用FLG:Y/N
     */
	@TableField("use_flg")
	private String useFlg;
    /**
     * 見積希望金額(税抜)
     */
	@TableField("bp_est_hope_amt_without_tax")
	private BigDecimal bpEstHopeAmtWithoutTax;
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
     * 見積依頼内容
     */
	@TableField("bp_est_req_content")
	private String bpEstReqContent;
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
     * 稟議書NO
     */
	@TableField("appr_doc_no")
	private String apprDocNo;
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


	public Integer getBpEstReqSendId() {
		return bpEstReqSendId;
	}

	public void setBpEstReqSendId(Integer bpEstReqSendId) {
		this.bpEstReqSendId = bpEstReqSendId;
	}

	public Date getSendDt() {
		return sendDt;
	}

	public void setSendDt(Date sendDt) {
		this.sendDt = sendDt;
	}

	public Integer getBpEstReqId() {
		return bpEstReqId;
	}

	public void setBpEstReqId(Integer bpEstReqId) {
		this.bpEstReqId = bpEstReqId;
	}

	public String getBpEstReqNo() {
		return bpEstReqNo;
	}

	public void setBpEstReqNo(String bpEstReqNo) {
		this.bpEstReqNo = bpEstReqNo;
	}

	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	public BigDecimal getBpEstHopeAmtWithoutTax() {
		return bpEstHopeAmtWithoutTax;
	}

	public void setBpEstHopeAmtWithoutTax(BigDecimal bpEstHopeAmtWithoutTax) {
		this.bpEstHopeAmtWithoutTax = bpEstHopeAmtWithoutTax;
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

	public String getBpEstReqContent() {
		return bpEstReqContent;
	}

	public void setBpEstReqContent(String bpEstReqContent) {
		this.bpEstReqContent = bpEstReqContent;
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

	public String getApprDocNo() {
		return apprDocNo;
	}

	public void setApprDocNo(String apprDocNo) {
		this.apprDocNo = apprDocNo;
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
		return this.bpEstReqSendId;
	}

}
