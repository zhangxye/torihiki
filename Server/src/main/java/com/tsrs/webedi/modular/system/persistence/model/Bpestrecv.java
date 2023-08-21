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
 * BP見積受信情報
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Bpestrecv extends Model<Bpestrecv> {

    private static final long serialVersionUID = 1L;

    /**
     * BP見積受信ID
     */
	@TableId(value="bp_est_recv_id", type= IdType.AUTO)
	private Integer bpEstRecvId;
    /**
     * 受信日時
     */
	@TableField("recv_dt")
	private Date recvDt;
    /**
     * BP見積NO
     */
	@TableField("bp_est_no")
	private String bpEstNo;
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
     * 見積金額(税抜)
     */
	@TableField("bp_est_amt_without_tax")
	private BigDecimal bpEstAmtWithoutTax;
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
     * 見積内容
     */
	@TableField("bp_est_content")
	private String bpEstContent;
    /**
     * 特記事項
     */
	@TableField("special_affairs")
	private String specialAffairs;
    /**
     * 備考
     */
	private String remark;
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


	public Integer getBpEstRecvId() {
		return bpEstRecvId;
	}

	public void setBpEstRecvId(Integer bpEstRecvId) {
		this.bpEstRecvId = bpEstRecvId;
	}

	public Date getRecvDt() {
		return recvDt;
	}

	public void setRecvDt(Date recvDt) {
		this.recvDt = recvDt;
	}

	public String getBpEstNo() {
		return bpEstNo;
	}

	public void setBpEstNo(String bpEstNo) {
		this.bpEstNo = bpEstNo;
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

	public BigDecimal getBpEstAmtWithoutTax() {
		return bpEstAmtWithoutTax;
	}

	public void setBpEstAmtWithoutTax(BigDecimal bpEstAmtWithoutTax) {
		this.bpEstAmtWithoutTax = bpEstAmtWithoutTax;
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

	public String getBpEstContent() {
		return bpEstContent;
	}

	public void setBpEstContent(String bpEstContent) {
		this.bpEstContent = bpEstContent;
	}

	public String getSpecialAffairs() {
		return specialAffairs;
	}

	public void setSpecialAffairs(String specialAffairs) {
		this.specialAffairs = specialAffairs;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return this.bpEstRecvId;
	}

}
