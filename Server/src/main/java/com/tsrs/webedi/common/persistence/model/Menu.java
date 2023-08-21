package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;;

/**
 * <p>
 * メニュー情報
 * </p>
 *
 * @author tsrs
 * @since 2018-03-02
 */
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * メニューid
     */
    @TableId(value="menu_id", type= IdType.AUTO)
	private Integer menuId;
    /**
     * メニュー名
     */
	@TableField("menu_nm")
	private String menuNm;
    /**
     * 使用FLG:Y:使用
N:削除
     */
	@TableField("use_flg")
	private String useFlg;
    /**
     * PATH
     */
	@TableField("menu_path")
	private String menuPath;
    /**
     * COMPONENT
     */
	@TableField("menu_component")
	private String menuComponent;
    /**
     * ICON
     */
	@TableField("menu_icon")
	private String menuIcon;
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


	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}


	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
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
		return this.menuId;
	}
	
	

	public String getMenuComponent() {
		return menuComponent;
	}

	public void setMenuComponent(String menuComponent) {
		this.menuComponent = menuComponent;
	}

	@Override
	public String toString() {
		return "Menu{" +
			"menuId=" + menuId +
			", menuNm=" + menuNm +
			", useFlg=" + useFlg +
			", menuPath=" + menuPath +
			", menuComponet=" + menuComponent +
			", menuIcon=" + menuIcon +
			", sysInsUserId=" + sysInsUserId +
			", sysInsDt=" + sysInsDt +
			", sysInsPgId=" + sysInsPgId +
			", sysUpdUserId=" + sysUpdUserId +
			", sysUpdDt=" + sysUpdDt +
			", sysUpdPgId=" + sysUpdPgId +
			"}";
	}
}
