package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;;

/**
 * <p>
 * メニュー・機能対照表
 * </p>
 *
 * @author tsrs
 * @since 2018-03-02
 */
public class Menuprogram extends Model<Menuprogram> {

    private static final long serialVersionUID = 1L;

    /**
     * サブメニューid
     */
	@TableId(value="sub_menu_id", type= IdType.AUTO)
	private Integer subMenuId;
    /**
     * メニューid
     */
	@TableField("menu_id")
	private Integer menuId;
    /**
     * 機能ID
     */
	@TableField("pg_id")
	private String pgId;
    /**
     * 下位サブメニューid
     */
	@TableField("low_menu_id")
	private Integer lowMenuId;
    /**
     * 表示順
     */
	@TableField("display_order")
	private Integer displayOrder;
    /**
     * 表示FLG:Y:メニュー表示する
N:メニュー表示しない
     */
	@TableField("display_flg")
	private String displayFlg;
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


	public Integer getSubMenuId() {
		return subMenuId;
	}

	public void setSubMenuId(Integer subMenuId) {
		this.subMenuId = subMenuId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public Integer getLowMenuId() {
		return lowMenuId;
	}

	public void setLowMenuId(Integer lowMenuId) {
		this.lowMenuId = lowMenuId;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDisplayFlg() {
		return displayFlg;
	}

	public void setDisplayFlg(String displayFlg) {
		this.displayFlg = displayFlg;
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
		return this.subMenuId;
	}

	@Override
	public String toString() {
		return "Menuprogram{" +
			"subMenuId=" + subMenuId +
			", menuId=" + menuId +
			", pgId=" + pgId +
			", lowMenuId=" + lowMenuId +
			", displayOrder=" + displayOrder +
			", displayFlg=" + displayFlg +
			", sysInsUserId=" + sysInsUserId +
			", sysInsDt=" + sysInsDt +
			", sysInsPgId=" + sysInsPgId +
			", sysUpdUserId=" + sysUpdUserId +
			", sysUpdDt=" + sysUpdDt +
			", sysUpdPgId=" + sysUpdPgId +
			"}";
	}
}
