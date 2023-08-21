package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 通勤経路Dtl
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Apprcommuteroutedtl extends Model<Apprcommuteroutedtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 通勤経路ID
     */
	@TableId(value="appr_commute_route_id", type= IdType.AUTO)
	private Integer apprCommuteRouteId;
    /**
     * 稟議書ID
     */
	@TableField("appr_doc_id")
	private Integer apprDocId;
    /**
     * 自宅最寄駅の線
     */
	@TableField("from_home_line_nm")
	private String fromHomeLineNm;
    /**
     * 自宅最寄駅の駅
     */
	@TableField("from_home_station_nm")
	private String fromHomeStationNm;
    /**
     * 現場最寄駅の線
     */
	@TableField("to_comp_line_nm")
	private String toCompLineNm;
    /**
     * 現場最寄駅の駅
     */
	@TableField("to_comp_station_nm")
	private String toCompStationNm;
    /**
     * 通勤経路の交通費
     */
	@TableField("travel_cost")
	private BigDecimal travelCost;
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


	public Integer getApprCommuteRouteId() {
		return apprCommuteRouteId;
	}

	public void setApprCommuteRouteId(Integer apprCommuteRouteId) {
		this.apprCommuteRouteId = apprCommuteRouteId;
	}

	public Integer getApprDocId() {
		return apprDocId;
	}

	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
	}

	public String getFromHomeLineNm() {
		return fromHomeLineNm;
	}

	public void setFromHomeLineNm(String fromHomeLineNm) {
		this.fromHomeLineNm = fromHomeLineNm;
	}

	public String getFromHomeStationNm() {
		return fromHomeStationNm;
	}

	public void setFromHomeStationNm(String fromHomeStationNm) {
		this.fromHomeStationNm = fromHomeStationNm;
	}

	public String getToCompLineNm() {
		return toCompLineNm;
	}

	public void setToCompLineNm(String toCompLineNm) {
		this.toCompLineNm = toCompLineNm;
	}

	public String getToCompStationNm() {
		return toCompStationNm;
	}

	public void setToCompStationNm(String toCompStationNm) {
		this.toCompStationNm = toCompStationNm;
	}

	public BigDecimal getTravelCost() {
		return travelCost;
	}

	public void setTravelCost(BigDecimal travelCost) {
		this.travelCost = travelCost;
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
		return this.apprCommuteRouteId;
	}

}
