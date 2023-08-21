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
 * 設定
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Syssetting extends Model<Syssetting> {

    private static final long serialVersionUID = 1L;

    /**
     * 設定ID
     */
	@TableId(value="sys_setting_id", type= IdType.AUTO)
	private Integer sysSettingId;
    /**
     * システムC
     */
	@TableField("sys_cd")
	private String sysCd;
    /**
     * 大分類C
     */
	@TableField("major_class_cd")
	private String majorClassCd;
    /**
     * 中分類C
     */
	@TableField("mid_class_cd")
	private String midClassCd;
    /**
     * 小分類C
     */
	@TableField("minor_class_cd")
	private String minorClassCd;
    /**
     * 文字列1
     */
	@TableField("str_val1")
	private String strVal1;
    /**
     * 文字列2
     */
	@TableField("str_val2")
	private String strVal2;
    /**
     * 文字列3
     */
	@TableField("str_val3")
	private String strVal3;
    /**
     * 文字列4
     */
	@TableField("str_val4")
	private String strVal4;
    /**
     * 文字列5
     */
	@TableField("str_val5")
	private String strVal5;
    /**
     * 数値1
     */
	@TableField("num_val1")
	private BigDecimal numVal1;
    /**
     * 数値2
     */
	@TableField("num_val2")
	private BigDecimal numVal2;
    /**
     * 数値3
     */
	@TableField("num_val3")
	private BigDecimal numVal3;
    /**
     * 数値4
     */
	@TableField("num_val4")
	private BigDecimal numVal4;
    /**
     * 数値5
     */
	@TableField("num_val5")
	private BigDecimal numVal5;
    /**
     * 備考1
     */
	private String remark1;
    /**
     * 備考2
     */
	private String remark2;
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


	public Integer getSysSettingId() {
		return sysSettingId;
	}

	public void setSysSettingId(Integer sysSettingId) {
		this.sysSettingId = sysSettingId;
	}

	public String getSysCd() {
		return sysCd;
	}

	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}

	public String getMajorClassCd() {
		return majorClassCd;
	}

	public void setMajorClassCd(String majorClassCd) {
		this.majorClassCd = majorClassCd;
	}

	public String getMidClassCd() {
		return midClassCd;
	}

	public void setMidClassCd(String midClassCd) {
		this.midClassCd = midClassCd;
	}

	public String getMinorClassCd() {
		return minorClassCd;
	}

	public void setMinorClassCd(String minorClassCd) {
		this.minorClassCd = minorClassCd;
	}

	public String getStrVal1() {
		return strVal1;
	}

	public void setStrVal1(String strVal1) {
		this.strVal1 = strVal1;
	}

	public String getStrVal2() {
		return strVal2;
	}

	public void setStrVal2(String strVal2) {
		this.strVal2 = strVal2;
	}

	public String getStrVal3() {
		return strVal3;
	}

	public void setStrVal3(String strVal3) {
		this.strVal3 = strVal3;
	}

	public String getStrVal4() {
		return strVal4;
	}

	public void setStrVal4(String strVal4) {
		this.strVal4 = strVal4;
	}

	public String getStrVal5() {
		return strVal5;
	}

	public void setStrVal5(String strVal5) {
		this.strVal5 = strVal5;
	}

	public BigDecimal getNumVal1() {
		return numVal1;
	}

	public void setNumVal1(BigDecimal numVal1) {
		this.numVal1 = numVal1;
	}

	public BigDecimal getNumVal2() {
		return numVal2;
	}

	public void setNumVal2(BigDecimal numVal2) {
		this.numVal2 = numVal2;
	}

	public BigDecimal getNumVal3() {
		return numVal3;
	}

	public void setNumVal3(BigDecimal numVal3) {
		this.numVal3 = numVal3;
	}

	public BigDecimal getNumVal4() {
		return numVal4;
	}

	public void setNumVal4(BigDecimal numVal4) {
		this.numVal4 = numVal4;
	}

	public BigDecimal getNumVal5() {
		return numVal5;
	}

	public void setNumVal5(BigDecimal numVal5) {
		this.numVal5 = numVal5;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
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
		return this.sysSettingId;
	}

}
