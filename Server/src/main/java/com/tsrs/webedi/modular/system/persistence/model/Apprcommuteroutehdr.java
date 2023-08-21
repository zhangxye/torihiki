package com.tsrs.webedi.modular.system.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 通勤経路Hdr
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Apprcommuteroutehdr extends Model<Apprcommuteroutehdr> {

    private static final long serialVersionUID = 1L;

    /**
     * 稟議書ID
     */
    @TableId(value="appr_doc_id", type= IdType.INPUT)
	private Integer apprDocId;
    /**
     * 交通費日額（往復）
     */
	@TableField("day_travel_cost")
	private BigDecimal dayTravelCost;
    /**
     * 1ヶ月の通勤代（定期）
     */
	@TableField("month_pass_travel_cost")
	private BigDecimal monthPassTravelCost;
    /**
     * 支払上限
     */
	@TableField("pay_travel_costa_max")
	private BigDecimal payTravelCostaMax;
    /**
     * 自宅→現場（乗り換え含め交通経路記入）
     */
	@TableField("rount_dlt_content")
	private String rountDltContent;
    /**
     * 登録者
     */
	@TableField(value="sys_ins_user_id", fill = FieldFill.INSERT)
	private String sysInsUserId;
    /**
     * 登録日時
     */
	@TableField(value="sys_ins_dt", fill = FieldFill.INSERT)
	private Date sysInsDt;
    /**
     * 登録機能ID
     */
	@TableField(value="sys_ins_pg_id", fill = FieldFill.INSERT)
	private String sysInsPgId;
    /**
     * 更新者
     */
	@TableField(value="sys_upd_user_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdUserId;
    /**
     * 更新日時
     */
	@TableField(value="sys_upd_dt", fill = FieldFill.INSERT_UPDATE)
	private Date sysUpdDt;
    /**
     * 更新機能ID
     */
	@TableField(value="sys_upd_pg_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdPgId;

	public Integer getApprDocId() {
		return apprDocId;
	}

	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
	}

	public BigDecimal getDayTravelCost() {
		return dayTravelCost;
	}

	public void setDayTravelCost(BigDecimal dayTravelCost) {
		this.dayTravelCost = dayTravelCost;
	}

	public BigDecimal getMonthPassTravelCost() {
		return monthPassTravelCost;
	}

	public void setMonthPassTravelCost(BigDecimal monthPassTravelCost) {
		this.monthPassTravelCost = monthPassTravelCost;
	}

	public BigDecimal getPayTravelCostaMax() {
		return payTravelCostaMax;
	}

	public void setPayTravelCostaMax(BigDecimal payTravelCostaMax) {
		this.payTravelCostaMax = payTravelCostaMax;
	}

	public String getRountDltContent() {
		return rountDltContent;
	}

	public void setRountDltContent(String rountDltContent) {
		this.rountDltContent = rountDltContent;
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
		return this.apprDocId;
	}

}
